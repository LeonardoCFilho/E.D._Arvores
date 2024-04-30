import java.util.ArrayList;
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
                        System.out.println("Valeu, falou");
                        break;
                
                    case 1:
                        Arvore newArvore = new Arvore(); //Constrói árvore
                        floresta.add(newArvore); //Adiciona ao vetor
                        break;
                        
                    case 2:
                        opArvore = Arvore.escolhaArvore(floresta); 
                        if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                            Node.leituraEmOrdem(floresta.get(opArvore).getRaiz());
                        }
                        break;
                
                    case 3: //Insercao para diferentes tipos de árvore vai ser feito em inserirArvore
                        opArvore = Arvore.escolhaArvore(floresta);
                            if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                                floresta.get(opArvore).inserirNode(floresta.get(opArvore).getRaiz(),(new Node(scan)));
                            }
                        break;
                
                    case 4:
                        opArvore = Arvore.escolhaArvore(floresta);
                        if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                            JOptionPane.showMessageDialog(null, "Alunos exibidos no terminal", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            Node.leituraEmOrdem(floresta.get(opArvore).getRaiz());
                            System.out.println("Insira a matricula do aluno a ser removido");
                            int tempInt = 0;
                            while(tempInt < 196000000){
                                tempInt = Integer.parseInt(JOptionPane.showInputDialog(null,"Matricula","Remocao",JOptionPane.INFORMATION_MESSAGE)); //Lendo a matricula
                                if(tempInt < 196000000){ //Caso de erro
                                    JOptionPane.showMessageDialog(null, "Matricula invalida", "Erro", JOptionPane.ERROR_MESSAGE); 
                                }
                            }
                            check = floresta.get(opArvore).removeNode(null,floresta.get(opArvore).getRaiz(), tempInt); 
                            if(check == false){ //Node não foi removido
                                System.out.println();
                                JOptionPane.showMessageDialog(null, "Aluno nao encontrado!", "Remocao", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        break;

                    case 5:
                        opArvore = Arvore.escolhaArvore(floresta);
                        if(opArvore >= 0){ //Precisa de 1+ árvore(s)
                            int tempInt = 0;
                            while(tempInt < 196000000){
                                tempInt = Integer.parseInt(JOptionPane.showInputDialog(null,"Matricula","Busca",JOptionPane.INFORMATION_MESSAGE)); //Lendo a matricula
                                if(tempInt < 196000000){ //Caso de erro
                                    JOptionPane.showMessageDialog(null, "Matricula invalida", "Erro", JOptionPane.ERROR_MESSAGE); 
                                }
                            }
                            //Função mais geral
                            Node nodeProcurado = floresta.get(opArvore).buscaNode(floresta.get(opArvore).getRaiz(),tempInt);
                            if(nodeProcurado != null){ //Node encontrado
                                JOptionPane.showMessageDialog(null, nodeProcurado, "Busca", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Aluno nao encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
