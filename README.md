# 🌳 Splay Tree Assignment (Java)

### 📘 Description
- This project implements a **Splay Tree** in Java.
- A Splay Tree is a type of self-adjusting binary search tree that moves recently accessed elements to the root.
- Features include insertion, deletion, search, and tree printing.

---

### 📂 Files
- `SplayTreeAssignment.java` — Core implementation of the Splay Tree and helper functions.

---

### ⚙️ Features
- ✔️ Insert nodes and auto-splay to root.
- ✔️ Search operation moves node to root.
- ✔️ Pretty-print tree structure with indentation.
- ✔️ Node class contains `data`, `parent`, `left`, and `right` pointers.

---

### 🚀 How to Compile and Run

Make sure you have Java installed:

```bash
javac SplayTreeAssignment.java
java SplayTreeAssignment
If there's no main() method, add one with sample operations to test the structure.

🧪 Example Usage (Manual)
java
Copy
Edit
SplayTreeAssignment tree = new SplayTreeAssignment();
tree.insert(1);
tree.insert(89);
tree.insert(5);
tree.insert(41);
tree.insert(33);
tree.insert(67);
tree.insert(44);
tree.insert(98);
tree.printTree();
🖥️ Sample Output (Tree Visualization)
lua
Copy
Edit
R----1
     R----89
          L----5
          |    R----41
          |         L----33
          |         R----67
          |              L----44
          R----98
After some search or insertions, the tree may rebalance to:

lua
Copy
Edit
R----44
     L----33
     |    L----1
     |    |    R----5
     |    R----41
     R----89
          L----67
          R----98
Or even:

lua
Copy
Edit
R----1
     R----33
          R----44
               R----98
