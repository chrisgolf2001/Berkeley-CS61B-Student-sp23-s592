public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public int size(){
        if(rest == null){
            return 1;
        }else{
            return 1 + this.rest.size();
        }
    }

    public int iterativeSize(){
        IntList p = this;
        int depth = 0;

        while (p != null){
            p = p.rest;
            depth++;
        }
        return depth;
    }
    public static void main(String[] args) {
        IntList lst = new IntList(1, new IntList(2, new IntList(3, null)));
        System.out.println(lst.size());
        System.out.println(lst.iterativeSize());

    }
}


