
import java.util.Arrays;

/** class treeMap stores maps in a binary search tree
 * generics k and v with a K being abel to copmare its self to other k's
 * implements interface */
public class TreeMap<K extends Comparable<K>,V > implements TreeMapInterface<K,V> {
    /**  the root of the whole tree   */
    private TreeMapNode<K,V> overallRoot;

    /**   the size of the tree */
    private int size;


    public TreeMap() {
        overallRoot = null;
    }

    public int size(){
        return size;
    }

    public void clear(){
        overallRoot = null;
        size = 0;
    }
    @Override
    public V get(K key) {
        TreeMapNode<K,V> node = get(overallRoot, key);
        return  node.value;
    }



    private TreeMapNode<K, V> get(TreeMapNode<K,V> root, K key){
        if(key==null) throw new IllegalArgumentException("null values can not be used to get a Book");
        if(root!=null) {
            if (root.key.equals(key)) {
                return root;
            } else if (root.key.compareTo(key) >= 0) {
                return get(root.left, key);
            } else {
                return get(root.right, key);
            }
        }
        return new TreeMapNode<>(null,null);
    }

    @Override
    public void put(K key, V value) {
        overallRoot = put(overallRoot, key, value);
    }
    private TreeMapNode<K,V> put(TreeMapNode<K,V> root,K key, V value) {
        if(key==null) throw new IllegalArgumentException("null values can not be added");
        if (root == null) {
            root = new TreeMapNode<>(key, value);
            size++;
        }else if(root.key.compareTo(key) == 0){
            root.value=value;
        }
        else if (root.key.compareTo(key) > 0) {
            root.left = put(root.left, key,value);
        } else {
            root.right = put(root.right, key, value);
        }
        return root;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(overallRoot, key);
    }
    private boolean containsKey(TreeMapNode<K,V> root, K key) {
        if (root == null) {
            return false;
        } else {
            int compare = key.compareTo(root.key);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return containsKey(root.left, key);
            } else {   // compare > 0
                return containsKey(root.right, key);
            }
        }
    }

    @Override
    public K[] toKeyArray(K[] array) {
        //array=ensureCapacity(size,array);
       toKeyArray(overallRoot,array,0);
       //if(array.length>size) {
       //    array[size] = null;
       //}
       return array;
    }


    private int toKeyArray(TreeMapNode<K,V> root, K [] array, int index) {
        if (root.left != null) {
            index = toKeyArray(root.left, array, index);
        }
        array[index++] = root.key;
        if (root.right != null) {
            index = toKeyArray(root.right, array, index);
        }
        return index;
    }

    /**
     * ensures that the capacity of the passed array can fit all the keys from the tree if not creates another
     * one that can and copy all the content form the old array to the new one
     * @param num number of the keys in the tree
     * @param array the array that needs to be filled with the keys for the tree
     * @return an array filled with all the keys from the tree inorder
     */
    private K[] ensureCapacity(int num, K[] array){
        if(num>=array.length){
            int newCapacity = array.length * 2 + 1;
            if (num > newCapacity) {
                newCapacity = num;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
        return array;
    }






    // post: prints the data of the tree, one per line
//    public void print() {
//        printInorder(overallRoot);
//    }
//
    // post: prints the data of the tree using an inorder traversal
//    public void printInorder(TreeMapNode<K,V> root) {
//        if (root != null) {
//            printInorder(root.left);
//            System.out.println(root.key);
//            printInorder(root.right);
//        }
//    }

    private static class TreeMapNode<K,V> {
        public K key;
        public V value;                   // data stored in this node
        public TreeMapNode<K,V> left;   // left subtree
        public TreeMapNode<K,V> right;  //  right subtree

        // post: constructs a leaf node with given data
        public TreeMapNode(K key,V value) {
            this(key, value, null, null);
        }

        // post: constructs a node with the given data and links
        public TreeMapNode(K key, V value, TreeMapNode<K,V> left,
                           TreeMapNode<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
