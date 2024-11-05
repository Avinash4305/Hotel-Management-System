#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define ORDER 5  // B-Tree of order 5

// B-Tree node structure
typedef struct BTreeNode {
    int keys[ORDER - 1];
    struct BTreeNode *children[ORDER];
    int numKeys;
    bool isLeaf;
} BTreeNode;

// Function prototypes
BTreeNode *createNode(bool isLeaf);
void traverse(BTreeNode *root);
BTreeNode *searchBTree(BTreeNode *root, int key);
void insert(BTreeNode **root, int key);
void deleteNode(BTreeNode **root, int key);
void splitChild(BTreeNode *parent, int i, BTreeNode *child);
void insertNonFull(BTreeNode *node, int key);
int findKey(BTreeNode *node, int key);
void removeKey(BTreeNode *node, int key);
void removeFromLeaf(BTreeNode *node, int idx);
void removeFromNonLeaf(BTreeNode *node, int idx);
int getPredecessor(BTreeNode *node, int idx);
int getSuccessor(BTreeNode *node, int idx);
void fill(BTreeNode *node, int idx);
void borrowFromPrev(BTreeNode *node, int idx);
void borrowFromNext(BTreeNode *node, int idx);
void mergeNodes(BTreeNode *node, int idx);

// Creates a new B-Tree node
BTreeNode *createNode(bool isLeaf) {
    BTreeNode *newNode = (BTreeNode *)malloc(sizeof(BTreeNode));
    if (!newNode) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }
    newNode->isLeaf = isLeaf;
    newNode->numKeys = 0;
    for (int i = 0; i < ORDER; i++)
        newNode->children[i] = NULL;
    return newNode;
}

// Traverses the B-Tree in-order
void traverse(BTreeNode *root) {
    if (root != NULL) {
        int i;
        for (i = 0; i < root->numKeys; i++) {
            if (!root->isLeaf)
                traverse(root->children[i]);
            printf("%d ", root->keys[i]);
        }
        if (!root->isLeaf)
            traverse(root->children[i]);
    }
}

// Searches for a key in the B-Tree
BTreeNode *searchBTree(BTreeNode *root, int key) {
    if (root == NULL)
        return NULL;

    int i = 0;
    while (i < root->numKeys && key > root->keys[i])
        i++;

    if (i < root->numKeys && root->keys[i] == key)
        return root;

    if (root->isLeaf)
        return NULL;

    return searchBTree(root->children[i], key);
}

// Inserts a key into the B-Tree
void insert(BTreeNode **root, int key) {
    if (*root == NULL) {
        *root = createNode(true);
        (*root)->keys[0] = key;
        (*root)->numKeys = 1;
    } else {
        if ((*root)->numKeys == ORDER - 1) {
            BTreeNode *newRoot = createNode(false);
            newRoot->children[0] = *root;
            splitChild(newRoot, 0, *root);
            int i = (newRoot->keys[0] < key) ? 1 : 0;
            insertNonFull(newRoot->children[i], key);
            *root = newRoot;
        } else {
            insertNonFull(*root, key);
        }
    }
}

// Splits a full child node
void splitChild(BTreeNode *parent, int i, BTreeNode *child) {
    BTreeNode *newNode = createNode(child->isLeaf);
    newNode->numKeys = ORDER / 2 - 1;

    // Copy the last (ORDER/2 -1) keys of child to newNode
    for (int j = 0; j < ORDER / 2 - 1; j++)
        newNode->keys[j] = child->keys[j + ORDER / 2];

    // Copy the last (ORDER/2) children of child to newNode
    if (!child->isLeaf) {
        for (int j = 0; j < ORDER / 2; j++)
            newNode->children[j] = child->children[j + ORDER / 2];
    }

    child->numKeys = ORDER / 2 - 1;

    // Create space for newNode in parent
    for (int j = parent->numKeys; j >= i + 1; j--)
        parent->children[j + 1] = parent->children[j];
    parent->children[i + 1] = newNode;

    // Move the middle key of child up to parent
    for (int j = parent->numKeys - 1; j >= i; j--)
        parent->keys[j + 1] = parent->keys[j];
    parent->keys[i] = child->keys[ORDER / 2 - 1];
    parent->numKeys++;
}

// Inserts a key into a node that is not full
void insertNonFull(BTreeNode *node, int key) {
    int i = node->numKeys - 1;

    if (node->isLeaf) {
        // Find the location to insert the new key
        while (i >= 0 && node->keys[i] > key) {
            node->keys[i + 1] = node->keys[i];
            i--;
        }
        node->keys[i + 1] = key;
        node->numKeys++;
    } else {
        // Find the child which is going to have the new key
        while (i >= 0 && node->keys[i] > key)
            i--;

        // Check if the found child is full
        if (node->children[i + 1]->numKeys == ORDER - 1) {
            splitChild(node, i + 1, node->children[i + 1]);

            // After split, the middle key moves up and node->keys[i+1] is the new key
            if (node->keys[i + 1] < key)
                i++;
        }
        insertNonFull(node->children[i + 1], key);
    }
}

// Finds the index of the first key greater than or equal to key
int findKey(BTreeNode *node, int key) {
    int idx = 0;
    while (idx < node->numKeys && node->keys[idx] < key)
        ++idx;
    return idx;
}

// Removes a key from the B-Tree
void deleteNode(BTreeNode **root, int key) {
    if (*root == NULL) {
        printf("The tree is empty.\n");
        return;
    }

    removeKey(*root, key);

    if ((*root)->numKeys == 0) {
        BTreeNode *tmp = *root;
        if ((*root)->isLeaf)
            *root = NULL;
        else
            *root = (*root)->children[0];
        free(tmp);
    }
}

// Removes a key from a node
void removeKey(BTreeNode *node, int key) {
    int idx = findKey(node, key);

    if (idx < node->numKeys && node->keys[idx] == key) {
        if (node->isLeaf)
            removeFromLeaf(node, idx);
        else
            removeFromNonLeaf(node, idx);
    } else {
        if (node->isLeaf) {
            printf("The key %d does not exist in the tree.\n", key);
            return;
        }

        bool flag = ((idx == node->numKeys) ? true : false);

        if (node->children[idx]->numKeys < ORDER / 2)
            fill(node, idx);

        if (flag && idx > node->numKeys)
            removeKey(node->children[idx - 1], key);
        else
            removeKey(node->children[idx], key);
    }
}

// Removes a key from a leaf node
void removeFromLeaf(BTreeNode *node, int idx) {
    for (int i = idx + 1; i < node->numKeys; ++i)
        node->keys[i - 1] = node->keys[i];
    node->numKeys--;
}

// Removes a key from a non-leaf node
void removeFromNonLeaf(BTreeNode *node, int idx) {
    int key = node->keys[idx];

    if (node->children[idx]->numKeys >= ORDER / 2) {
        int pred = getPredecessor(node, idx);
        node->keys[idx] = pred;
        removeKey(node->children[idx], pred);
    } else if (node->children[idx + 1]->numKeys >= ORDER / 2) {
        int succ = getSuccessor(node, idx);
        node->keys[idx] = succ;
        removeKey(node->children[idx + 1], succ);
    } else {
        mergeNodes(node, idx);
        removeKey(node->children[idx], key);
    }
}

// Gets the predecessor of keys[idx]
int getPredecessor(BTreeNode *node, int idx) {
    BTreeNode *current = node->children[idx];
    while (!current->isLeaf)
        current = current->children[current->numKeys];
    return current->keys[current->numKeys - 1];
}

// Gets the successor of keys[idx]
int getSuccessor(BTreeNode *node, int idx) {
    BTreeNode *current = node->children[idx + 1];
    while (!current->isLeaf)
        current = current->children[0];
    return current->keys[0];
}

// Fills child node at idx which has less than ORDER/2 -1 keys
void fill(BTreeNode *node, int idx) {
    if (idx != 0 && node->children[idx - 1]->numKeys >= ORDER / 2)
        borrowFromPrev(node, idx);
    else if (idx != node->numKeys && node->children[idx + 1]->numKeys >= ORDER / 2)
        borrowFromNext(node, idx);
    else {
        if (idx != node->numKeys)
            mergeNodes(node, idx);
        else
            mergeNodes(node, idx - 1);
    }
}

// Borrows a key from children[idx-1] and inserts it into children[idx]
void borrowFromPrev(BTreeNode *node, int idx) {
    BTreeNode *child = node->children[idx];
    BTreeNode *sibling = node->children[idx - 1];

    // Move all keys in child one step ahead
    for (int i = child->numKeys - 1; i >= 0; --i)
        child->keys[i + 1] = child->keys[i];

    // If child is not a leaf, move its child pointers
    if (!child->isLeaf) {
        for (int i = child->numKeys; i >= 0; --i)
            child->children[i + 1] = child->children[i];
    }

    // Set child's first key equal to node's key[idx-1]
    child->keys[0] = node->keys[idx - 1];

    // If not leaf, move sibling's last child to child
    if (!child->isLeaf)
        child->children[0] = sibling->children[sibling->numKeys];

    // Move the last key from sibling up to parent
    node->keys[idx - 1] = sibling->keys[sibling->numKeys - 1];

    child->numKeys += 1;
    sibling->numKeys -= 1;
}

// Borrows a key from children[idx+1] and inserts it into children[idx]
void borrowFromNext(BTreeNode *node, int idx) {
    BTreeNode *child = node->children[idx];
    BTreeNode *sibling = node->children[idx + 1];

    // node's key[idx] is inserted as the last key in child
    child->keys[child->numKeys] = node->keys[idx];

    // If child is not leaf, sibling's first child is appended to child
    if (!child->isLeaf)
        child->children[child->numKeys + 1] = sibling->children[0];

    // Move the first key from sibling up to parent
    node->keys[idx] = sibling->keys[0];

    // Shift all keys in sibling to the left
    for (int i = 1; i < sibling->numKeys; ++i)
        sibling->keys[i - 1] = sibling->keys[i];

    // Shift the child pointers in sibling to the left
    if (!sibling->isLeaf) {
        for (int i = 1; i <= sibling->numKeys; ++i)
            sibling->children[i - 1] = sibling->children[i];
    }

    child->numKeys += 1;
    sibling->numKeys -= 1;
}

// Merges children[idx] with children[idx+1]
void mergeNodes(BTreeNode *node, int idx) {
    BTreeNode *child = node->children[idx];
    BTreeNode *sibling = node->children[idx + 1];

    // Pull down the key from node and insert it into (ORDER/2-1) position of child
    child->keys[ORDER / 2 - 1] = node->keys[idx];

    // Copy keys from sibling to child
    for (int i = 0; i < sibling->numKeys; ++i)
        child->keys[i + ORDER / 2] = sibling->keys[i];

    // Copy child pointers from sibling to child
    if (!child->isLeaf) {
        for (int i = 0; i <= sibling->numKeys; ++i)
            child->children[i + ORDER / 2] = sibling->children[i];
    }

    // Shift keys and children in node to fill the gap
    for (int i = idx + 1; i < node->numKeys; ++i)
        node->keys[i - 1] = node->keys[i];
    for (int i = idx + 2; i <= node->numKeys; ++i)
        node->children[i - 1] = node->children[i];

    // Update the number of keys in child and node
    child->numKeys += sibling->numKeys + 1;
    node->numKeys--;

    // Free the sibling
    free(sibling);
}

// Main function to test B-Tree operations
int main() {
    BTreeNode *root = NULL;

    // Seed the random number generator for reproducibility
    srand(42);

    // Insert 100 random elements
    printf("Inserting 100 random elements into the B-Tree...\n");
    for (int i = 0; i < 100; i++) {
        int key = rand() % 1000; // Random keys in the range 0-999
        printf("Inserting %d\n", key);
        insert(&root, key);
    }

    printf("\nB-Tree traversal after insertion:\n");
    traverse(root);
    printf("\n");

    // Search for a key
    int searchKey = rand() % 1000;
    printf("\nSearching for key %d in the B-Tree...\n", searchKey);
    BTreeNode *result = searchBTree(root, searchKey);
    if (result != NULL)
        printf("Key %d found in the B-Tree.\n", searchKey);
    else
        printf("Key %d not found in the B-Tree.\n", searchKey);

    // Delete a key
    int deleteKey = rand() % 1000;
    printf("\nDeleting key %d from the B-Tree...\n", deleteKey);
    deleteNode(&root, deleteKey);
    printf("B-Tree traversal after deletion:\n");
    traverse(root);
    printf("\n");

    // Optionally, perform more operations or display the tree structure
    return 0;
}
