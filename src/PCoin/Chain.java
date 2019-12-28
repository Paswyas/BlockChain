package PCoin;

import java.util.LinkedList;

public class Chain {

    LinkedList<Block> chain;
    int difficulty;

    Chain(){

        this.chain = new LinkedList<>();
        chain.add(new Block("我是祖先", "null"));
        this.difficulty = 5;
    }

    void addBlockToChain(Block newBlock){
        //block.previousHash = getLastBlock().hash;
        Block block = new Block("", chain.getLast().hash);
        block.mine(difficulty);
        newBlock = block;
        chain.addLast(newBlock);
    }

    boolean validateChain(){
        if (chain.size() == 1){
            if (!chain.getFirst().hash.equals(chain.getFirst().computeHash())){
                return false;
            }
            return true;
        }

        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).hash.equals(chain.get(i).computeHash())){
                System.out.println(chain.get(i).hash);
                System.out.println(Hash.getSHA256(chain.get(i).data+"null"));
                System.out.println(chain.get(i).computeHash());
                System.out.println("数据遭篡改");
                return false;
            }

            if (!chain.get(i-1).hash.equals(chain.get(i).previousHash)){
                System.out.println("前后区块链接断裂");
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder().append("Chain: {\n");
        for (int i = 0; i < chain.size(); i++) {
            sb.append(" "+chain.get(i));
        }
        return sb.append("}\n").toString();
    }

    public static void main(String[] args) {

        Chain chain = new Chain();
        //chain.chain.add(new Block("转账十元", "a93ab262bdf59e1bc75e786189c32fea72a0ab9830ffcaf0207ffc3589d29a0d"));
        chain.addBlockToChain(new Block("转账二十元"));
        chain.addBlockToChain(new Block("转账三十元"));
        /*chain.chain.get(1).data = "转账100元";
        chain.chain.get(1).mine(5);*/
        System.out.println(chain);
        System.out.println(chain.validateChain());
    }
}
