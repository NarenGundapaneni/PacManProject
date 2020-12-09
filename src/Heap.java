/*

public class Heap<E extends Comparable> extends ListBinaryTree<E> {
    public Heap() {
    }

    public void add(E data) {
        super.add(data);
        this.addHelper(this.size() - 1);
    }

    private void addHelper(int index) {
        if (this.getParentIndex(index) != -1 && ((Comparable)this.list.get(index)).compareTo(this.list.get(this.getParentIndex(index))) < 0) {
            this.swap(index, this.getParentIndex(index));
            this.addHelper(this.getParentIndex(index));
        }

    }

    public boolean meetsHeapProperty(int index) {
        if (index == -1) {
            return false;
        } else {
            E parent = (Comparable)this.list.get(index);
            E leftChild = null;
            E rightChild = null;
            if (this.getLeftIndex(index) != -1 && this.getLeftIndex(index) < this.size()) {
                leftChild = (Comparable)this.list.get(this.getLeftIndex(index));
            }

            if (this.getRightIndex(index) != -1 && this.getRightIndex(index) < this.size()) {
                rightChild = (Comparable)this.list.get(this.getRightIndex(index));
            }

            if (leftChild == null && rightChild == null) {
                return true;
            } else if (leftChild == null && rightChild != null) {
                return parent.compareTo(rightChild) < 0;
            } else if (leftChild != null && rightChild == null) {
                return parent.compareTo(leftChild) < 0;
            } else {
                return parent.compareTo(leftChild) < 0 && parent.compareTo(rightChild) < 0;
            }
        }
    }

    private int getSmallestChildIndex(int index) {
        if (index != -1 && index < this.size()) {
            E parent = (Comparable)this.list.get(index);
            E leftChild = null;
            E rightChild = null;
            if (this.getLeftIndex(index) != -1 && this.getLeftIndex(index) < this.size()) {
                leftChild = (Comparable)this.list.get(this.getLeftIndex(index));
            }

            if (this.getRightIndex(index) != -1 && this.getRightIndex(index) < this.size()) {
                rightChild = (Comparable)this.list.get(this.getRightIndex(index));
            }

            if (leftChild == null && rightChild == null) {
                return -1;
            } else if (leftChild == null && rightChild != null) {
                return this.getRightIndex(index);
            } else if (leftChild != null && rightChild == null) {
                return this.getLeftIndex(index);
            } else {
                return leftChild.compareTo(rightChild) < 0 ? this.getLeftIndex(index) : this.getRightIndex(index);
            }
        } else {
            return -1;
        }
    }

    public E removeRoot() {
        if (this.size() == 0) {
            return null;
        } else {
            E ret = (Comparable)this.list.get(0);
            this.list.set(0, this.removeLast());
            this.heapify();
            return ret;
        }
    }

    public void heapify() {
        for(int i = this.size() - 1; i >= 0; --i) {
            this.sink(i);
        }

    }

    private void sink(int index) {
        if (!this.meetsHeapProperty(index)) {
            int smallIndex = this.getSmallestChildIndex(index);
            this.swap(index, smallIndex);
            this.sink(smallIndex);
        }

    }

    public void shuffle() {
        for(int i = 0; i < this.size(); ++i) {
            this.swap(i, (int)(Math.random() * (double)this.size()));
        }

    }
}
*/