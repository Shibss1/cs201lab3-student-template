import java.util.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          final String[] traversals = {traversal1, traversal2, traversal3};
          String answer = "";

          if (traversal1.length() == traversal2.length() && 
          traversal1.length() == traversal3.length() && 
          traversal2.length() == traversal3.length()) {
               for (String traversal : traversals) {
                    Node root = buildBst(traversal);
                    String[] routes = {preOrder(root), postOrder(root), inOrder(root)};

                    answer = match(traversals, routes);

                    if (answer != null) {
                         return answer;
                    }
               }
          }

          return "Invalid traversals";
     }

     public static class Node {
          int value;
          Node left, right;

          Node(int value) {
               this.value = value;
          }
     }

     public static Node buildBst(String traversal) {
          Node root = null;
          for (String toBuild : traversal.split("-")) {
               root = insert(root, Integer.parseInt(toBuild));
          }

          return root;
     }

     public static Node insert(Node node, int value) {
          if (node == null) {
               return new Node(value);
          }

          if (value < node.value) {
               node.left = insert(node.left, value);
          } else {
               node.right = insert(node.right, value);
          }
          
          return node;
     }

     public static String preOrder(Node node) {
          if (node == null) return "";
          return join(String.valueOf(node.value), preOrder(node.left), preOrder(node.right));
     }

     public static String postOrder(Node node) {
          if (node == null) return "";
          return join(postOrder(node.left), postOrder(node.right), String.valueOf(node.value));
     }

     public static String inOrder(Node node) {
          if (node == null) return "";
          return join(inOrder(node.left), String.valueOf(node.value), inOrder(node.right));
     }

     public static String join(String... parts) {
          StringJoiner joiner = new StringJoiner("-");

          for (String part : parts) {
               if (!part.isEmpty()) {
                    joiner.add(part);
               }
          }

          return joiner.toString();
     }

     public static String match(String[] givenRoutes, String[] generatedRoutes) {
          String[] names = {"Preorder", "Postorder", "Inorder"};
          String[] results = new String[givenRoutes.length];

          for (int i = 0; i < givenRoutes.length; i++) {
               for (int j = 0; j < generatedRoutes.length; j++) {
                    if (givenRoutes[i].equals(generatedRoutes[j])) {
                         results[i] = names[j];
                         break;
                    }
               }

               if (results[i] == null) {
                    return null;
               }
          }

          return "Traversal 1 - " + results[0] + ", " +
          "Traversal 2 - " + results[1] + ", " +
          "Traversal 3 - " + results[2];
     }

}
