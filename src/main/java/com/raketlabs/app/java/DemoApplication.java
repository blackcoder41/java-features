package com.raketlabs.app.java;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class DemoApplication {

	public static void main(String[] args) throws Exception {
		httpClient();


	}

	/**
	 * JEP 321: JEP 321: HTTP Client<br>
	 * Release: 10<br>
	 * Standardize the incubated HTTP Client API introduced in JDK 9, via JEP 110, and updated in JDK 10.<br>
	 * <a href="https://openjdk.org/jeps/321">JEP 321: JEP 321: HTTP Client</a>
	 */
	public static void httpClient() throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https://postman-echo.com/get"))
				.GET()
				.build();

		HttpResponse<String> response = HttpClient.newHttpClient()
				.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());
	}

	/**
	 * JEP 286: Local-Variable Type Inference<br>
	 * Release: 10<br>
	 * Enhance the Java Language to extend type inference to declarations of local variables with initializers.<br>
	 * <a href="https://openjdk.org/jeps/361">JEP 286: Local-Variable Type Inference</a>
	 */
	public static void localVariableTypeInference() {
		var list = new ArrayList<String>();  // infers ArrayList<String>
		var stream = list.stream();          // infers Stream<String>
	}

	/**
	 * JEP 361: Switch Expressions<br>
	 * Release: 14<br>
	 * Extend switch so it can be used as either a statement or an expression, and so that both forms can use either
	 * traditional case ... : labels (with fall through) or new case ... -> labels (with no fall through), with a
	 * further new statement for yielding a value from a switch expression. These changes will simplify everyday
	 * coding, and prepare the way for the use of pattern matching in switch. This was a preview language feature
	 * in JDK 12 and JDK 13.<br>
	 * <a href="https://openjdk.org/jeps/361">JEP 361: Switch Expressions</a>
	 */
	public static void switchExpressions() {
		DayOfWeek day = LocalDate.now().getDayOfWeek();
		switch (day) {
			case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
			case TUESDAY                -> System.out.println(7);
			case THURSDAY, SATURDAY     -> System.out.println(8);
			case WEDNESDAY              -> System.out.println(9);
		}

		int numLetters = switch (day) {
			case MONDAY, FRIDAY, SUNDAY -> 6;
			case TUESDAY                -> 7;
			case THURSDAY, SATURDAY     -> 8;
			case WEDNESDAY              -> 9;
		};

		int k = new Random().nextInt(10);
		System.out.println(
				switch (k) {
					case  1 -> "one";
					case  2 -> "two";
					default -> "many";
				}
		);

		int j = switch (day) {
			case MONDAY  -> 0;
			case TUESDAY -> 1;
			default      -> {
				int length = day.toString().length();
				int result = new Random().nextInt(length);
				yield result;
			}
		};
	}

	/**
	 * JEP 378: Text Blocks<br>
	 * Release: 15<br>
	 * Add text blocks to the Java language. A text block is a multi-line string literal that avoids the need for most
	 * escape sequences, automatically formats the string in a predictable way, and gives the developer control over
	 * the format when desired.<br>
	 * <a href="https://openjdk.org/jeps/378">JEP 378: Text Blocks</a>
	 */
	public static void textBlocks() throws ScriptException {
		// HTML example
		// Using "one-dimensional" string literals
		String html = "<html>\n" +
				"    <body>\n" +
				"        <p>Hello, world</p>\n" +
				"    </body>\n" +
				"</html>\n";

		// SQL example
		// Using "one-dimensional" string literals
		String query = "SELECT \"EMP_ID\", \"LAST_NAME\" FROM \"EMPLOYEE_TB\"\n" +
				"WHERE \"CITY\" = 'INDIANAPOLIS'\n" +
				"ORDER BY \"EMP_ID\", \"LAST_NAME\";\n";

		// Using a "two-dimensional" block of text
		String query2 = """
               SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
               WHERE "CITY" = 'INDIANAPOLIS'
               ORDER BY "EMP_ID", "LAST_NAME";
               """;

		// Polyglot language example
		// Using "one-dimensional" string literals

		ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
		Object obj = engine.eval("function hello() {\n" +
				"    print('\"Hello, world\"');\n" +
				"}\n" +
				"\n" +
				"hello();\n");

		// Using a "two-dimensional" block of text
		ScriptEngine engine2 = new ScriptEngineManager().getEngineByName("js");
		Object obj2 = engine2.eval("""
                         function hello() {
                             print('"Hello, world"');
                         }
                         
                         hello();
                         """);
	}

	/**
	 * JEP 394: Pattern Matching for instanceof<br>
	 * Release: 16<br>
	 * Enhance the Java programming language with pattern matching for the instanceof operator. Pattern matching allows
	 * common logic in a program, namely the conditional extraction of components from objects, to be expressed more
	 * concisely and safely.<br>
	 * <a href="https://openjdk.org/jeps/394">JEP 394: Pattern Matching for instanceof</a>
	 */
	public static void patternMatchingForInstanceof() {
		Object number = BigDecimal.valueOf(12.34);
		if (number instanceof Number n) {
			System.out.println(n.intValue());
		}
	}
}
