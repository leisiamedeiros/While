package plp.enquanto;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import plp.enquanto.parser.EnquantoLexer;
import plp.enquanto.parser.EnquantoParser;
import plp.enquanto.parser.MeuListener;
import static plp.enquanto.linguagem.Linguagem.*;
import static java.util.Arrays.*;

public class Principal {

	private static ParseTree parse(String programa) {
		final ANTLRInputStream input = new ANTLRInputStream(programa);
		final EnquantoLexer lexer = new EnquantoLexer(input);
		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final EnquantoParser parser = new EnquantoParser(tokens);
		return parser.programa();
	}

	public static void main(String... args) throws IOException {
		String programa = "x:=10; y:=leia ; c:= x + y; "
				+ "se 30<=c entao escreva c senaose c = 15 entao exiba \"igual a 15\" senao exiba \"menor\"";
		final ParseTree tree = parse(programa);
		final ParseTreeWalker walker = new ParseTreeWalker();
		final MeuListener listener = new MeuListener();
		walker.walk(listener, tree);
		
		Programa p1 = listener.getPrograma();
		// O parser devolve um objeto 'Programa' semelhante ao programa a seguir:
		//p1.execute();
		
		String programa2 = "i:=0; "+"para i de 1 ate 5 faca escreva i";
		final ParseTree tree2 = parse(programa2);
		walker.walk(listener, tree2);
		
		Programa p2 = listener.getPrograma();
		//p2.execute();
		
		String programa3 = "se verdadeiro xor verdadeiro entao exiba \"true\" senao exiba \"false\"";
		final ParseTree tree3 = parse(programa3);
		walker.walk(listener, tree3);
		
		Programa p3 = listener.getPrograma();
		p3.execute();
	}
}
