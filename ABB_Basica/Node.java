import java.util.Scanner; //Scanner(System.in)

class Node{
    private int chave;
    private String nomeNode;
    //Dados extras casos necessário
    private Node esquerda;
    private Node direita;

    public Node(){ //Cria um node vazio
        chave = 0;
        nomeNode = null;
        esquerda = direita = null;
    }

    public Node(Scanner scan){ //Construtor do 0
        System.out.print("Insira a chave do elemento: "); //Chave
        do{
            chave = Integer.parseInt(scan.nextLine());
            if(chave < 0){
                System.out.print("Insira uma chave valida: ");
            }
        }while(chave < 0);
        System.out.print("Insira o nome do elemento: "); //Nome
        do{
            nomeNode = scan.nextLine();
            if(nomeNode.length() == 0){
                System.out.print("Insira um nome valido: ");
            }
        }while(nomeNode.length() == 0);
        esquerda = direita = null; //Arrumando os ponteiros
    }

    public Node(int chave, String nomeNode){
        this.chave = chave;
        this.nomeNode = nomeNode;
        esquerda = direita = null; //Arrumando os ponteiros
    }

    public Node(Node newInfo){ //Basicamente copia um node
        chave = newInfo.getChave();
        nomeNode = newInfo.getNomeElemento();
    }

    //Lista de get
    public int getChave(){
        return chave;
    }
    public String getNomeElemento(){
        return nomeNode;
    }
    public Node getEsquerda(){
        return esquerda;
    }
    public Node getDireita(){
        return direita;
    }

    //Lista de set
    
    /* public void setChave(int x){ //Fiz isso aqui por princípio mesmo, usar ela é uma péssima ideia
        chave = x;
    } */
    public void setNomeElemento(String novoNome){ 
        nomeNode = novoNome;
    }
    public void setEsquerda(Node newNode){
        esquerda = newNode;
    }
    public void setDireita(Node newNode){
        direita = newNode;
    }

    public String toString(){ //Imprime as informações do elemento
        return 
            "Elemento: " + nomeNode + "\n" +
            "Chave: " + chave; //Sem "\n" para mais liberdade na impressão
    }

    public static void leituraEmOrdem(Node no){ //E R D
        if(no != null){
            leituraEmOrdem(no.esquerda);
            System.out.println(no);
            leituraEmOrdem(no.direita);
        }
    }
}