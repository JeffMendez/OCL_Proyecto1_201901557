
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package analizadores;

import java_cup.runtime.*;
import java.util.ArrayList;
import modelos.ExpresionEvaluar;
import modelos.Conjunto;
import modelos.Tree;
import modelos.node;
import modelos.followTable;
import modelos.transitionTable;
import modelos.ErrorFile;
import modelos.Expresion;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Sintactico extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Sintactico() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Sintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Sintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\025\000\002\002\004\000\002\002\005\000\002\003" +
    "\010\000\002\004\004\000\002\004\003\000\002\005\004" +
    "\000\002\005\003\000\002\007\006\000\002\006\013\000" +
    "\002\006\012\000\002\006\007\000\002\010\005\000\002" +
    "\010\004\000\002\011\005\000\002\011\005\000\002\011" +
    "\004\000\002\011\004\000\002\011\004\000\002\011\005" +
    "\000\002\011\003\000\002\011\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\071\000\004\012\004\001\002\000\006\004\012\026" +
    "\007\001\002\000\004\002\006\001\002\000\004\002\001" +
    "\001\002\000\004\007\047\001\002\000\010\004\012\011" +
    "\033\026\007\001\002\000\010\004\ufffd\011\ufffd\026\ufffd" +
    "\001\002\000\004\006\015\001\002\000\004\013\014\001" +
    "\002\000\004\002\000\001\002\000\004\026\016\001\002" +
    "\000\004\007\017\001\002\000\004\010\020\001\002\000" +
    "\004\025\021\001\002\000\006\021\023\024\022\001\002" +
    "\000\004\025\031\001\002\000\004\025\030\001\002\000" +
    "\006\005\026\021\025\001\002\000\004\025\027\001\002" +
    "\000\010\004\ufff8\011\ufff8\026\ufff8\001\002\000\006\005" +
    "\ufff6\021\ufff6\001\002\000\006\005\ufff5\021\ufff5\001\002" +
    "\000\004\005\032\001\002\000\010\004\ufff9\011\ufff9\026" +
    "\ufff9\001\002\000\004\011\035\001\002\000\010\004\ufffe" +
    "\011\ufffe\026\ufffe\001\002\000\004\011\036\001\002\000" +
    "\004\011\037\001\002\000\004\026\040\001\002\000\004" +
    "\006\044\001\002\000\006\013\ufffb\026\ufffb\001\002\000" +
    "\006\013\uffff\026\040\001\002\000\006\013\ufffc\026\ufffc" +
    "\001\002\000\004\022\045\001\002\000\004\005\046\001" +
    "\002\000\006\013\ufffa\026\ufffa\001\002\000\004\010\050" +
    "\001\002\000\022\012\056\014\061\015\054\016\055\017" +
    "\052\020\053\022\051\023\060\001\002\000\024\005\uffee" +
    "\012\uffee\014\uffee\015\uffee\016\uffee\017\uffee\020\uffee\022" +
    "\uffee\023\uffee\001\002\000\022\012\056\014\061\015\054" +
    "\016\055\017\052\020\053\022\051\023\060\001\002\000" +
    "\022\012\056\014\061\015\054\016\055\017\052\020\053" +
    "\022\051\023\060\001\002\000\022\012\056\014\061\015" +
    "\054\016\055\017\052\020\053\022\051\023\060\001\002" +
    "\000\022\012\056\014\061\015\054\016\055\017\052\020" +
    "\053\022\051\023\060\001\002\000\004\026\064\001\002" +
    "\000\004\005\063\001\002\000\024\005\uffed\012\uffed\014" +
    "\uffed\015\uffed\016\uffed\017\uffed\020\uffed\022\uffed\023\uffed" +
    "\001\002\000\022\012\056\014\061\015\054\016\055\017" +
    "\052\020\053\022\051\023\060\001\002\000\024\005\ufff1" +
    "\012\ufff1\014\ufff1\015\ufff1\016\ufff1\017\ufff1\020\ufff1\022" +
    "\ufff1\023\ufff1\001\002\000\010\004\ufff7\011\ufff7\026\ufff7" +
    "\001\002\000\004\013\065\001\002\000\024\005\uffef\012" +
    "\uffef\014\uffef\015\uffef\016\uffef\017\uffef\020\uffef\022\uffef" +
    "\023\uffef\001\002\000\024\005\ufff2\012\ufff2\014\ufff2\015" +
    "\ufff2\016\ufff2\017\ufff2\020\ufff2\022\ufff2\023\ufff2\001\002" +
    "\000\024\005\ufff0\012\ufff0\014\ufff0\015\ufff0\016\ufff0\017" +
    "\ufff0\020\ufff0\022\ufff0\023\ufff0\001\002\000\022\012\056" +
    "\014\061\015\054\016\055\017\052\020\053\022\051\023" +
    "\060\001\002\000\024\005\ufff4\012\ufff4\014\ufff4\015\ufff4" +
    "\016\ufff4\017\ufff4\020\ufff4\022\ufff4\023\ufff4\001\002\000" +
    "\022\012\056\014\061\015\054\016\055\017\052\020\053" +
    "\022\051\023\060\001\002\000\024\005\ufff3\012\ufff3\014" +
    "\ufff3\015\ufff3\016\ufff3\017\ufff3\020\ufff3\022\ufff3\023\ufff3" +
    "\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\071\000\004\002\004\001\001\000\010\003\012\004" +
    "\007\006\010\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\006\033\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\010\023\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\005\041" +
    "\007\040\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\007\042\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\011\056\001\001\000\002\001\001\000\004\011\071" +
    "\001\001\000\004\011\067\001\001\000\004\011\066\001" +
    "\001\000\004\011\065\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\011\061\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\011" +
    "\070\001\001\000\002\001\001\000\004\011\072\001\001" +
    "\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Sintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Sintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Sintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    public static ArrayList<ExpresionEvaluar> listaExpEvaluar = new ArrayList<ExpresionEvaluar>();
    public static ArrayList<Expresion> listaExpresiones = new ArrayList<Expresion>();
    public static ArrayList<Conjunto> listaConjuntos = new ArrayList<Conjunto>();
    public static ArrayList<ErrorFile> listaErrores = new ArrayList<ErrorFile>();

    /**
     * Método al que se llama automáticamente ante algún error sintactico.
     **/ 
    public void syntax_error(Symbol s){ 
        int fila = s.left;
        int columna = s.right;

        ErrorFile newError = new ErrorFile(fila, columna, s.value.toString(), "Sintactico");
        listaErrores.add(newError);
        
        System.out.println("Error Sintáctico en la Línea " + (s.left) +
        " Columna "+s.right+ ". No se esperaba este componente: " +s.value+"."); 
    } 

    /**
     * Método al que se llama automáticamente ante algún error sintáctico 
     * en el que ya no es posible una recuperación de errores.
     **/ 
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{ 
        int fila = s.left;
        int columna = s.right;

        ErrorFile newError = new ErrorFile(fila, columna, s.value.toString(), "Sintactico");
        listaErrores.add(newError);
        
        System.out.println("Error síntactico irrecuperable en la Línea " + 
        (s.left)+ " Columna "+s.right+". Componente " + s.value + 
        " no reconocido."); 
    }  


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Sintactico$actions {
  private final Sintactico parser;

  /** Constructor */
  CUP$Sintactico$actions(Sintactico parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Sintactico$do_action_part00000000(
    int                        CUP$Sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Sintactico$parser,
    java.util.Stack            CUP$Sintactico$stack,
    int                        CUP$Sintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Sintactico$result;

      /* select the action based on the action number */
      switch (CUP$Sintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		RESULT = start_val;
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Sintactico$parser.done_parsing();
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= llaveIzq CONTENIDO llaveDer 
            {
              Object RESULT =null;
		
        System.out.println("¡El analisis fue completado!");
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("INICIO",0, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // CONTENIDO ::= DECLARACIONES porcentaje porcentaje porcentaje porcentaje EVALUACIONES 
            {
              Object RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("CONTENIDO",1, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-5)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // DECLARACIONES ::= DECLARACIONES DECLARACION 
            {
              Object RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACIONES",2, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // DECLARACIONES ::= DECLARACION 
            {
              Object RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACIONES",2, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // EVALUACIONES ::= EVALUACIONES EVALUACION 
            {
              Object RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EVALUACIONES",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // EVALUACIONES ::= EVALUACION 
            {
              Object RESULT =null;

              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EVALUACIONES",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // EVALUACION ::= ID dospuntos CADENA ptcoma 
            {
              Object RESULT =null;
		int nombre_expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).left;
		int nombre_expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).right;
		String nombre_exp = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).value;
		int valorleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int valorright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		String valor = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		
        ExpresionEvaluar nuevaExpEvaluar = new ExpresionEvaluar(nombre_exp, valor.substring(1,valor.length()-1));
        listaExpEvaluar.add(nuevaExpEvaluar);
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EVALUACION",5, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // DECLARACION ::= CONJ dospuntos ID menos mayor ASCII virgu ASCII ptcoma 
            {
              Object RESULT =null;
		int nombre_conjleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-6)).left;
		int nombre_conjright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-6)).right;
		String nombre_conj = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-6)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		
        Conjunto nuevoConj = new Conjunto(nombre_conj, a, b);
        listaConjuntos.add(nuevoConj);
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACION",4, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-8)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // DECLARACION ::= CONJ dospuntos ID menos mayor ASCII LISTACARACTERES ptcoma 
            {
              Object RESULT =null;
		int nombre_conjleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-5)).left;
		int nombre_conjright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-5)).right;
		String nombre_conj = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-5)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int conjuntoleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int conjuntoright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Conjunto conjunto = (Conjunto)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		
        conjunto.agregarElemento(a);
        conjunto.setNombre(nombre_conj);
        listaConjuntos.add(conjunto);
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACION",4, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-7)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // DECLARACION ::= ID menos mayor EXPRESION ptcoma 
            {
              Object RESULT =null;
		int nombre_exprleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)).left;
		int nombre_exprright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)).right;
		String nombre_expr = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)).value;
		int exprleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int exprright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Object expr = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		
        String expresion = "." + expr + "#";
        
        System.out.println(expresion);
        /*ArrayList<node> leaves = new ArrayList();
        ArrayList<ArrayList> table = new ArrayList();
        Tree arbol = new Tree(expresion, leaves, table, nombre_expr);

        node raiz = arbol.getRoot();
        raiz.getNode();
        raiz.follow();
        arbol.crearGrafoArbol();
        
        followTable ft = new followTable(nombre_expr);
        ft.crearGrafoSiguientes(table);
        
        transitionTable tran = new transitionTable(raiz, table, leaves, nombre_expr);
        tran.crearGrafoTransiciones();
        tran.crearGrafoAutomata();

        Expresion nuevaExpReg = new Expresion(nombre_expr, raiz, tran.states);
        listaExpresiones.add(nuevaExpReg);

        ft.printTable(table);
        tran.impTable();*/
        System.out.println("-------------------------------");
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("DECLARACION",4, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // LISTACARACTERES ::= LISTACARACTERES coma ASCII 
            {
              Conjunto RESULT =null;
		int listaleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int listaright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Conjunto lista = (Conjunto)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        lista.agregarElemento(a);
        RESULT = lista;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("LISTACARACTERES",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // LISTACARACTERES ::= coma ASCII 
            {
              Conjunto RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = new Conjunto();
        RESULT.agregarElemento(a);
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("LISTACARACTERES",6, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // EXPRESION ::= punto EXPRESION EXPRESION 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = "."+a+b;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // EXPRESION ::= pleca EXPRESION EXPRESION 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = "|"+a+b;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // EXPRESION ::= asterisco EXPRESION 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = "*"+a;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // EXPRESION ::= mas EXPRESION 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = "+"+a;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // EXPRESION ::= interrogacion EXPRESION 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = "?"+a;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // EXPRESION ::= llaveIzq ID llaveDer 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		
        RESULT = "{" + id + "}";
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // EXPRESION ::= CADENA 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = a;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // EXPRESION ::= CARACTER 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		
        RESULT = a;
    
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Sintactico$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Sintactico$do_action(
    int                        CUP$Sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Sintactico$parser,
    java.util.Stack            CUP$Sintactico$stack,
    int                        CUP$Sintactico$top)
    throws java.lang.Exception
    {
              return CUP$Sintactico$do_action_part00000000(
                               CUP$Sintactico$act_num,
                               CUP$Sintactico$parser,
                               CUP$Sintactico$stack,
                               CUP$Sintactico$top);
    }
}

}
