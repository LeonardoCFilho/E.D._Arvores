import java.util.ArrayList;
//import java.util.Scanner;
import javax.swing.JOptionPane;

public class Arvore{
    private Node raiz;
    private String nomeArvore;

    public Arvore(){ //Árvore vazia, scan nome
        raiz = null;
        nomeArvore = JOptionPane.showInputDialog(null, "Nomeie a arvore: ", "Nova arvore", JOptionPane.PLAIN_MESSAGE);
        while(nomeArvore.length() == 0){ //Nome vazio
            nomeArvore = JOptionPane.showInputDialog(null, "Nomeie a arvore: ", "Nova arvore", JOptionPane.PLAIN_MESSAGE);
        } 
    }
    public Arvore(String nomeArvore){ //Nomeando árvore sem input
        raiz = null;
        this.nomeArvore = nomeArvore;
    }
    //Gets
    public String getNomeArvore(){
        return nomeArvore;
    }
    public Node getRaiz(){
        return raiz;
    }
    //Sets
    public void setNomeArvore(String novoNome){
        nomeArvore = novoNome;
    }
    public void setRaiz(Node novoNo){ //Mudando a raiz
        raiz = novoNo;
    }

    public static int escolhaArvore(ArrayList<Arvore> floresta){ //-1 -> sair, 0+ -> índice
        int escolha = -1; 
        String[] strNomes = new String[floresta.size()+1];
        strNomes[0] = "0. Retornar ao menu principal";
            for(int i = 0; i < floresta.size(); i++){
                strNomes[i+1] = "Arvore " + (i+1) + ": " + floresta.get(i).getNomeArvore();
            }
        String tempOp = (String) JOptionPane.showInputDialog(null, "Escolha um item", "Menu", JOptionPane.INFORMATION_MESSAGE, null, strNomes, strNomes[0]);

        if(tempOp != null && tempOp.charAt(0) != '0'){
            tempOp = tempOp.split("Arvore ", 2)[1]; //Retirando caracteres anteriores ao int
            escolha = Integer.parseInt(tempOp.split(": ", 2)[0]); //Retirando caracteres posteriores ao int
            escolha--; 
        }
        else{
            escolha = -1;
        }
        return escolha;
    }

    public void inserirNode(Node noDestino, Node newNode){
        if(noDestino != null){ //Não é a raiz
            if(noDestino.getChave() > newNode.getChave()){ //Ir para a esquerda
                if(noDestino.getEsquerda() != null){ //Recursão
                    inserirNode(noDestino.getEsquerda(), newNode);
                }
                else{ //Achou posição válida
                    noDestino.setEsquerda(newNode);
                }
            }
            if(noDestino.getChave() < newNode.getChave()){ //Ir para a direita
                if(noDestino.getDireita() != null){ //Recursão
                    inserirNode(noDestino.getDireita(), newNode);
                }
                else{ //Achou posição válida
                    noDestino.setDireita(newNode);
                }
            }
        }
        else{ //Nova raiz
            raiz = new Node(newNode);
        }
    }

    public boolean removeNode(Node noPai, Node noAtual, int chave){ //Chave é a matrícula do indivíduo que será removido
        boolean sucess = true;
        if (noAtual != null) {
            //Procurando o Node
            if(chave > noAtual.getChave()){ //Vai para a direita
                removeNode(noAtual, noAtual.getDireita(), chave);
            }
            else if(chave < noAtual.getChave()){ //Vai para a esquerda
                removeNode(noAtual, noAtual.getEsquerda(), chave);
            }
            
            //Node encontrado
            else {
                if(noAtual.getEsquerda() == null){ //Possivel pai de um
                    if(noPai == null){ //É a raiz
                        raiz = noAtual.getDireita(); //Trocando a raiz
                    }
                    else{ //Não é a raiz
                        if(noPai.getEsquerda() == noAtual){ //Filho está na esquerda
                            noPai.setEsquerda(noAtual.getDireita());
                        }
                        else{ //Filho está na direita
                            noPai.setDireita(noAtual.getDireita());
                        }
                    }
                }
                else{//Tem filho a esquerda
                    if(noAtual.getDireita() == null){ //Pai de um
                        if(noPai == null){ //É a raiz
                            raiz = noAtual.getEsquerda(); //Trocando a raiz
                        }
                        else{ //Não é a raiz
                            if(noPai.getEsquerda() == noAtual){ //Filho está na esquerda
                                noPai.setEsquerda(noAtual.getEsquerda());
                            }
                            else{ //Filho está na direita
                                noPai.setDireita(noAtual.getEsquerda());
                            }
                        }
                    }
                    else{ //Pai de 2
                        Node maiorEsquerda = noAtual.getEsquerda(); //Maior da esquerda dessa vez 
                        while(maiorEsquerda.getDireita() != null){
                            maiorEsquerda = maiorEsquerda.getDireita(); //Realmente achando o maior
                        }
    
                        //Salvando os ponteiros
                        Node temp = new Node(noAtual); //Inicializando temp
                        temp.setEsquerda(noAtual.getEsquerda()); 
                        temp.setDireita(noAtual.getDireita());
    
                        //Copiando as informações de maiorEsquerda
                        noAtual = new Node(maiorEsquerda); 
    
                        //Restaurando os ponteiros
                        noAtual.setEsquerda(temp.getEsquerda()); 
                        noAtual.setDireita(temp.getDireita());
    
                        removeNode(noAtual, noAtual.getEsquerda(), maiorEsquerda.getChave()); //Removendo temp de sua posição antiga
                    }
                }
            }
        }
        else{
            sucess = false;
        }
        return sucess;
    }

    public Node buscaNode(Node noAtual, int chaveProcurada){
        if(noAtual != null){ //Recursão até achar o node ou nada
            if(noAtual.getChave() > chaveProcurada){ //Ir para a esquerda
                buscaNode(noAtual.getEsquerda(), chaveProcurada);
            }
            if(noAtual.getChave() < chaveProcurada){ //Ir para a direita
                buscaNode(noAtual.getDireita(), chaveProcurada);
            }
        }
        return noAtual; //Retorna null se não achar
    }

    public void mergeArvores(Arvore arvoreFinal, Node arvoreSecundaria){
        if(arvoreSecundaria != null){ //Percorre a árvore secundária em ordem e insere os seus elementos na árvore final
            mergeArvores(arvoreFinal, arvoreSecundaria.getEsquerda());
            inserirNode(arvoreFinal.raiz, new Node(arvoreSecundaria)); //Efetivamente reseta os ponteiros
            mergeArvores(arvoreFinal, arvoreSecundaria.getDireita());
        }
    }
}