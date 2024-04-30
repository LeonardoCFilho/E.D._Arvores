import java.util.ArrayList; //Vetor dinamico em java, add(), remove(), get(), size();
import java.util.Scanner; //Scanner(System.in)

class Node{
    private int chave; //Nesse caso, chave = matrícula
    private String nomeAluno;
    private ArrayList<Double> vetorNotas; //vetor dinamico
    private Node esquerda;
    private Node direita;

    public Node(){ //Cria um no vazio, pense bem antes de usar
        chave = 0;
        nomeAluno = null;
        vetorNotas = new ArrayList<>();
        esquerda = direita = null;
    }

    public Node(Scanner scan){ //Construtor do 0
        System.out.print("Insira a matricula do(a) aluno(a): "); //Matrícula/chave
        do{
            chave = Integer.parseInt(scan.nextLine());
            if(chave < 196000000){
                System.out.print("Insira uma matricula valida: ");
            }
        }while(chave < 196000000);
        System.out.print("Insira o nome do(a) aluno(a): "); //Nome
        do{
            nomeAluno = scan.nextLine();
            if(nomeAluno.length() == 0){
                System.out.print("Insira um nome valido: ");
            }
        }while(nomeAluno.length() == 0);
        int numNotas;
        System.out.print("Insira o numero de notas a ser registrado: "); 
        do{
            numNotas = Integer.parseInt(scan.nextLine());
            if(numNotas < 1){
                System.out.print("Insira uma quantidade valida: ");
            }
        }while(numNotas < 1);
        vetorNotas = new ArrayList<>();
        for(int i = 1; i <= numNotas; i++){ 
            System.out.print("Nota " + i + ": ");
            Double temp;
            do{
                temp = Double.parseDouble(scan.nextLine());
                if(temp < 0 || temp > 10){
                    System.out.print("Insira uma nota valida: ");
                }
            }while(temp < 0 || temp > 10); 
            vetorNotas.add(temp); //adicionando no vetor 
        }
        esquerda = direita = null; //Arrumando os ponteiros
    }

    public Node(Node newInfo){ //Basicamente copia um node
        chave = newInfo.getChave();
        nomeAluno = newInfo.getNomeAluno();
        while (vetorNotas != null){ //Soa desnecessário
            vetorNotas.remove(0);
        }
        vetorNotas = new ArrayList<>(newInfo.getVetorNotas()); 
        //esquerda = direita = null; //Necessário?
    }

    //Lista de get
    public int getChave(){
        return chave;
    }
    public String getNomeAluno(){
        return nomeAluno;
    }
    public ArrayList<Double> getVetorNotas(){ 
        return vetorNotas;
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
    public void setNomeAluno(String novoNome){ 
        nomeAluno = novoNome;
    }
    public void setNotasAluno(ArrayList<Double> novasNotas){
        while (vetorNotas != null){ 
            vetorNotas.remove(0);
        }
        vetorNotas = new ArrayList<>(novasNotas);
    }
    public void setEsquerda(Node newNode){
        esquerda = newNode;
    }
    public void setDireita(Node newNode){
        direita = newNode;
    }

    public void addNota(Double x){ //Adicionar notas para o aluno
        vetorNotas.add(x);
    }

    public String toString(){ //Imprime as informações do cara
        String temp = new String();
        for(int i = 0; i < vetorNotas.size(); i++){
            temp += "Nota " + (i+1) + ": " + vetorNotas.get(i) + "\n";
        }

        return 
            "*******************************\n" +
            "Aluno: " + nomeAluno + "\n" +
            "Matrícula: " + chave + "\n"
            + temp;
    }

    public static void leituraEmOrdem(Node no){ //E R D
        if(no != null){
            leituraEmOrdem(no.esquerda);
            System.out.println(no);
            leituraEmOrdem(no.direita);
        }
    }
}