package PCoin;

public class Block {

    String data;
    String hash;
    String previousHash;
    int nonce;
    Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.hash = computeHash();
        this.nonce = 1;
    }

    Block(String data){
        this.data = data;
        this.hash = computeHash();
    }

    String computeHash(){
        return Hash.getSHA256(this.data+this.previousHash+this.nonce);
    }

    String getAnswer(int difficulty){
        String answer = "";
        for (int i = 0; i < difficulty; i++) {
            answer += "0";
        }
        return answer;
    }

    //计算符合要求难度的hash-工作量证明
    void mine(int difficulty){
        while (true){
            this.hash = computeHash();
            if (!this.hash.substring(0, difficulty).equals(getAnswer(difficulty))){
                this.nonce++;
            }else {
                break;
            }
        }
        System.out.println("挖矿结束 "+this.hash);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder().append("Block {\n");
        sb.append(" data: "+data+",\n");
        sb.append(" previousHash: "+previousHash+",\n");
        sb.append(" hash: "+hash+"\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Block("转账十元", "123"));
        System.out.println(new Block("我是祖先", "null"));
        //System.out.println(new Block("转账二十元", "a93ab262bdf59e1bc75e786189c32fea72a0ab9830ffcaf0207ffc3589d29a0d").hash);
        //System.out.println(Hash.getSHA256("转账二十元"+"a93ab262bdf59e1bc75e786189c32fea72a0ab9830ffcaf0207ffc3589d29a0d"));
    }
}
