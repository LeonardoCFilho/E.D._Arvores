import java.util.ArrayList; //Vetor dinamico em java, add(), remove(), get(), size();
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main{
    public static void main(String[] args){
        ArrayList<Arvore> floresta = new ArrayList<>(); //Já inicializando o vetor
        int op = -1, opArvore = -1; 
        boolean check;
        Scanner scan = new Scanner(System.in);
        do{
            String[] itens = { //Menu
                "0. Sair",
                "1. Criar arvore",
                "2. Imprimir arvore",
                "3. Inserir registro",    
                "4. Deletar registro",
                "5. Buscar elemento",
                "6. Merge arvores"
            };
            String tempOp = (String) JOptionPane.showInputDialog(null, "Escolha um item", "Menu", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

            if(tempOp != null){
                op = Integer.parseInt(tempOp.split(". ", 2)[0]); //Separando o int da String
                switch(op){
                    case 0:
                        System.out.println("Saindo...");
                        break;
                
                    case 1:
                        Arvore newArvore = new Arvore(); //Constrói árvore
                        floresta.add(newArvore); //Adiciona ao vetor
                        break;
                        
                    case 2:
                        opArvore = Arvore.escolhaArvore(floresta); 
                        if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                            System.out.println("**************************************************");
                            Node.leituraEmOrdem(floresta.get(opArvore).getRaiz());
                            System.out.println("**************************************************");
                        }
                        break;
                
                    case 3: 
                        opArvore = Arvore.escolhaArvore(floresta);
                            if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                                floresta.get(opArvore).inserirNode(floresta.get(opArvore).getRaiz(),(new Node(scan)));
                            }
                        break;
                
                    case 4:
                        opArvore = Arvore.escolhaArvore(floresta);
                        if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                            JOptionPane.showMessageDialog(null, "Elementos exibidos no terminal", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            Node.leituraEmOrdem(floresta.get(opArvore).getRaiz()); //Criar um usando JPanes depois
                            System.out.println("Insira a chave do elemento a ser removido");
                            int tempInt = -1;
                            while(tempInt < 0){
                                tempInt = Integer.parseInt(JOptionPane.showInputDialog(null,"Chave","Remocao",JOptionPane.INFORMATION_MESSAGE)); //Lendo a chave
                                if(tempInt < 0){ //Caso de erro
                                    JOptionPane.showMessageDialog(null, "Chave invalida", "Erro", JOptionPane.ERROR_MESSAGE); 
                                }
                            }
                            check = floresta.get(opArvore).removeNode(null,floresta.get(opArvore).getRaiz(), tempInt); 
                            if(check == false){ //Node não foi removido
                                System.out.println();
                                JOptionPane.showMessageDialog(null, "Elemento nao encontrado!", "Remocao", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Elemento removido", "Remocao", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        break;

                    case 5:
                        opArvore = Arvore.escolhaArvore(floresta);
                        if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                            int tempInt = -1;
                            while(tempInt < 0){
                                tempInt = Integer.parseInt(JOptionPane.showInputDialog(null,"Chave","Busca",JOptionPane.INFORMATION_MESSAGE)); //Lendo a chave
                                if(tempInt < 0){ //Caso de erro
                                    JOptionPane.showMessageDialog(null, "Chave invalida", "Erro", JOptionPane.ERROR_MESSAGE); 
                                }
                            }
                            Node nodeProcurado = floresta.get(opArvore).buscaNode(floresta.get(opArvore).getRaiz(),tempInt); //buscaNode retorna o Node inteiro
                            if(nodeProcurado != null){ //Node encontrado
                                JOptionPane.showMessageDialog(null, nodeProcurado, "Busca", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Elemento nao encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        break;

                    case 6:
                        JOptionPane.showMessageDialog(null, "A primeira arvore sera a unica mantida", "Aviso", JOptionPane.INFORMATION_MESSAGE); //Avisando qual será mantida
                        opArvore = Arvore.escolhaArvore(floresta);
                        if(opArvore >= 0){ 
                            int opArvore2 = Arvore.escolhaArvore(floresta); //Precisa de 2+ árvore(s)
                            if(opArvore2 >= 0){
                                while(opArvore == opArvore2){ //Não fazer a fusão da árvore com ela mesma
                                    JOptionPane.showMessageDialog(null, "Escolha duas arvores diferentes", "Erro", JOptionPane.ERROR_MESSAGE);
                                    opArvore2 = Arvore.escolhaArvore(floresta);
                                }
                                floresta.get(opArvore).mergeArvores(floresta.get(opArvore), floresta.get(opArvore2).getRaiz());
                                floresta.remove(opArvore2); //Removendo árvore 
                            }
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Indice recebido excede o limite", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
            else{
                op = 0; //Forçando saída
            }
        }while(op != 0);
        scan.close(); //Evitar resource leak
    }
}