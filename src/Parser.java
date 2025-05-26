public class Parser {

    public String cadena;  // Cadena a analizar
    public int i = 0;      // Índice actual en la cadena

    private void error(String regla) {
        System.out.println("Syntax error en " + regla);
        System.exit(1);
    }

    public Parser(String input) {
        this.cadena = input;
    }

    public void parsear(){
        E();
        if (cadena.charAt(i)  =='#' && i+1 ==cadena.length()) {
            System.out.println("Cadena valida!");
        }else {
            System.out.println("Cadena Invalida!");
        }
    }
    public void eat() {
        System.out.println("Consumido: " + cadena.charAt(i));
        i++;
    }

    public boolean match(char c) {
        return i < cadena.length() && cadena.charAt(i) == c;
    }


    public void E() {
        //"E  -> T E'"
        if (match('(')|| match('a')||match('b')|| match('c')){
            T();
            Eprima();
        }else {
            error("E");
        }

    }
    public void Eprima() {
        //"E' -> | T E' | λ"
        if (match('|')) {
            eat();
            T();
            Eprima();
        } else if (match(')') || match('#')) {
            return; // lambda
        } else {
            error("E'");
        }
    }

    public void T() {
        //"T  -> F T'"
        if (match('(') || match('a') || match('b') || match('c')) {
            F();
            Tprima();
        }else {
error("T");
        }

    }
    public void F() {
        //"F  -> P F'"
        if (match('(') || match('a') || match('b') || match('c')) {
            P();
            Fprima();
        } else {
            error("F");
        }

    }

    public void P() {
        //"P  -> (E) | L"
        if (match('(')) {
            eat();
            E();
            if (match(')')) {
                eat();
            } else {
                System.out.println("Syntax error: falta )");
                System.exit(1);
            }
        } else if (match('a') || match('b') || match('c')) {
            L();
        } else {
            error("P");
        }
    }


    public void L() {
        //"L  -> a | b | c"
        if (match('a') || match('b') || match('c')){
            eat();
        }else{
            error("L");
        }
    }
    public void Fprima() {
        //"F' -> * | λ"
        if (match('*')) {
            eat();
        } else if (match('.') || match('|') || match(')') || match('#')) {
            return; // lambda
        } else {
            error("F'");
        }
    }
    public void Tprima() {
        //"T' -> . F T' | λ"
        if (match('.')) {
            eat();
            F();
            Tprima();
        } else if (match('|') || match(')') || match('#')) {
            return; // lambda
        } else {
            error("T'");
        }
    }
}
