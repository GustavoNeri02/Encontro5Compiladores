package lexico;

import java.util.ArrayList;

public class Analizador {
    Entrada entrada;
    ArrayList<String> variaveis;

    public Analizador(String arquivo){
        entrada = new Entrada(arquivo);
        variaveis = new ArrayList<String>();
    }

    public Token proximoToken(){
        int caractereLido = -1;


        //ignorar espaços em branco e quebra de linha (return -1)
        while ((caractereLido = entrada.LerProximoCaractere()) != -1){
            char c = (char)caractereLido;
            if(c == ' '){
                continue;
            } else if( c == '\n'){
                continue;
            }

            //testes

            switch (c) {
                case ';':
                    return new Token(TipoToken.Delim, ";");
                case '/':
                    return new Token(TipoToken.OPRelDiv, "/");
                case '*':
                    return new Token(TipoToken.OPRelMulti, "*");
                case '(':
                    return new Token(TipoToken.AbreParen, "(");
                case ')':
                    return new Token(TipoToken.FechaParen, ")");
                case '{':
                    return new Token(TipoToken.AbreChave, "{");
                case '}':
                    return new Token(TipoToken.FechaChave, "}");
                case '\'':
                    return new Token(TipoToken.AspasSimples, "'");
                case '\"':
                    return new Token(TipoToken.AspasDuplas, "\"");
                case '+':
                    c = (char) entrada.LerProximoCaractere();
                    if (c == '+') {
                        return new Token(TipoToken.OPRelIncremento, "++");
                    } else if (c == '=') {
                        return new Token(TipoToken.OPRelMaisRecebe, "+=");
                    }
                    return new Token(TipoToken.OPRelSoma, "+");
                case '-':
                    c = (char) entrada.LerProximoCaractere();
                    if (c == '-') {
                        return new Token(TipoToken.OPRelDecremento, "--");
                    } else if (c == '=') {
                        return new Token(TipoToken.OPRelMenosRecebe, "-=");
                    }
                    return new Token(TipoToken.OPRelSubt, "-");
                case '=':
                    if (entrada.LerProximoCaractere() == '=') {
                        return new Token(TipoToken.OPRelIgual, "==");
                    } else {
                        return new Token(TipoToken.Recebe, "=");
                    }
                case '<':
                    if (entrada.LerProximoCaractere() == '=') {
                        return new Token(TipoToken.OPRelMenorIgual, "<=");
                    } else {
                        return new Token(TipoToken.OPRelMenor, "<");
                    }
                case '>':
                    if (entrada.LerProximoCaractere() == '=') {
                        return new Token(TipoToken.OPRelMaiorIgual, ">=");
                    } else {
                        return new Token(TipoToken.OPRelMaior, ">");
                    }
                case '!':
                    if (entrada.LerProximoCaractere() == '=') {
                        return new Token(TipoToken.OPRelDiferente, "!=");
                    } else {
                        return new Token(TipoToken.Negacao, "!");
                    }
                case '&':
                    if (entrada.LerProximoCaractere() == '&') {
                        return new Token(TipoToken.OPRelE, "&&");
                    }
                    break;
                case '|':
                    if (entrada.LerProximoCaractere() == '|') {
                        return new Token(TipoToken.OPRelOu, "||");
                    }
                    break;
                case 'i':
                    ArrayList<Character> characters = new ArrayList<>();
                    characters.add((char)c);
                    c = (char) entrada.LerProximoCaractere();
                    if ( c == 'n') {
                        if (entrada.LerProximoCaractere() == 't') {
                            System.out.println(new Token(TipoToken.ReservInt, "int"));
                            return NovaVariavel();

                        }
                    }else {
                        return VelhaVariavel(characters, c);
                    }
                    break;
                case 'S':
                    if (entrada.LerProximoCaractere() == 't') {
                        if (entrada.LerProximoCaractere() == 'r') {
                            if (entrada.LerProximoCaractere() == 'i') {
                                if (entrada.LerProximoCaractere() == 'n') {
                                    if (entrada.LerProximoCaractere() == 'g') {
                                        System.out.println(new Token(TipoToken.ReservString, "String"));
                                        return NovaVariavel();
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 'b':
                    if (entrada.LerProximoCaractere() == 'o') {
                        if (entrada.LerProximoCaractere() == 'o') {
                            if (entrada.LerProximoCaractere() == 'l') {
                                if (entrada.LerProximoCaractere() == 'e') {
                                    if (entrada.LerProximoCaractere() == 'a') {
                                        if (entrada.LerProximoCaractere() == 'n') {
                                            System.out.println(new Token(TipoToken.ReservBool, "bool"));
                                            return NovaVariavel();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 'c':
                    if (entrada.LerProximoCaractere() == 'h') {
                        if (entrada.LerProximoCaractere() == 'a') {
                            if (entrada.LerProximoCaractere() == 'r') {
                                System.out.println(new Token(TipoToken.ReservChar, "char"));
                                NovaVariavel();
                            }
                        }
                    }
                    break;
                case 'w':
                    if (entrada.LerProximoCaractere() == 'h') {
                        if (entrada.LerProximoCaractere() == 'i') {
                            if (entrada.LerProximoCaractere() == 'l') {
                                if (entrada.LerProximoCaractere() == 'e') {
                                    return new Token(TipoToken.ReservWhile, "While");
                                }
                            }
                        }
                    }
                    break;
                case 'f':
                    if (entrada.LerProximoCaractere() == 'a') {
                        if (entrada.LerProximoCaractere() == 'l') {
                            if (entrada.LerProximoCaractere() == 's') {
                                if (entrada.LerProximoCaractere() == 'e') {
                                    return new Token(TipoToken.ConstFalse, "False");
                                }
                            }
                        }
                    }
                    break;
                case 't':
                    if (entrada.LerProximoCaractere() == 'r') {
                        if (entrada.LerProximoCaractere() == 'u') {
                            if (entrada.LerProximoCaractere() == 'e') {
                                return new Token(TipoToken.ConstTrue, "True");
                            }
                        }
                    }
                    break;
                default:
                    if(Character.isDigit(c)){
                        return new Token(TipoToken.Inteiro, (String.valueOf(Character.getNumericValue(c))));
                    }
                    if (Character.isLetter(c)){
                        return VelhaVariavel(c);
                    }
                    break;
            }

        }
        return null;
    }

    private Token VelhaVariavel(char c) {
        String texto = "";
        while(c != ' ' && c != ';'){
            texto += c;
            c = (char) entrada.LerProximoCaractere();
        }
        if (variaveis.contains(texto)){
            if (c == ';'){
                System.out.println(new Token(TipoToken.Var, texto));
                return new Token(TipoToken.Delim, ";");
            }else{
                return new Token(TipoToken.Var, texto);
            }
        }
        return null;
    }

    private Token VelhaVariavel(ArrayList<Character> array, char c) {
        String texto = array.get(0).toString();
        while(c != ' ' && c != ';'){
            texto += c;
            c = (char) entrada.LerProximoCaractere();
        }
        if (variaveis.contains(texto)){
            if (c == ';'){
                System.out.println(new Token(TipoToken.Var, texto));
                return new Token(TipoToken.Delim, ";");
            }else{
                return new Token(TipoToken.Var, texto);
            }
        }
        return null;
    }

    private Token NovaVariavel() {
        String texto = "";
        char aux= (char) entrada.LerProximoCaractere();
        if (aux == ' '){
            aux = (char) entrada.LerProximoCaractere();
        }
        if(Character.isLetter(aux)){
            do{
                texto += aux;
                aux = (char) entrada.LerProximoCaractere();
            }while (aux != ' ' && aux != ';');
        }
        variaveis.add(texto);
        return new Token(TipoToken.Var, texto);
    }

}
