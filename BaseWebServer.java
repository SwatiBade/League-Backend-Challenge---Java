import com.sun.net.httpserver.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.net.*;
import java.util.*;


public class BaseWebServer {
 
    public static void main(String[] args) {
        try {
 
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
            httpServer.createContext("/echo", new EchoHandler());
            httpServer.createContext("/invert", new InvertHandler());
            httpServer.createContext("/flatten", new FlattenHandler());
            httpServer.createContext("/sum", new SumHandler());
            httpServer.createContext("/multiply", new MultiplyHandler());
            httpServer.start();
 
        } catch (IOException ex) {
            System.out.println("Exception in BaseWebServer: main():"+ex);
        }
 
    }
    static class EchoHandler implements HttpHandler {
 
        @Override
        public void handle(HttpExchange he) throws IOException {

            System.out.println("echo [Returns the matrix as a string in matrix format]:"+he.getRequestMethod());
            
            if (he.getRequestMethod().equalsIgnoreCase("POST")) {
                try {
                    List<List<Integer>> matrix = new ArrayList<>();
                    StringBuffer responseBuff = new StringBuffer();

                    InputStream reqBody = he.getRequestBody();
                    matrix = parseCSVRequestData(reqBody);

                    for(List<Integer> temp : matrix){
                        for(Integer val : temp){
                            responseBuff.append(val+" ");
                        }
                        responseBuff.append("\n");
                    }
                    String response = responseBuff.toString();
                    System.out.println(response);
                    he.sendResponseHeaders(200, response.length());
                    OutputStream output = he.getResponseBody();
                    output.write(response.getBytes());
                    output.close();
                    he.close();
 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(he.getRequestMethod().equalsIgnoreCase("GET")){
                String response = "Hi there!";
                he.sendResponseHeaders(200, response.getBytes().length);//response code and length
                OutputStream os = he.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }


    static class InvertHandler implements HttpHandler {
 
        @Override
        public void handle(HttpExchange he) throws IOException {

            System.out.println("Invert [Return the matrix as a string in matrix format where the columns and rows are inverted]:");
            if (he.getRequestMethod().equalsIgnoreCase("POST")) {
                try {
                    List<List<Integer>> matrix = new ArrayList<>();
                    int[][] inverMat = null;
                    StringBuffer responseBuff = new StringBuffer();
                    
                    InputStream reqBody = he.getRequestBody();
                    matrix = parseCSVRequestData(reqBody);
                    
                    inverMat = new int[matrix.size()][matrix.get(0).size()];
                    for( int k=0;k< matrix.size();k++){
                        List<Integer> temp = matrix.get(k);
                        for(int i=0;i< temp.size();i++){
                            inverMat[i][k] = temp.get(i);
                        }
                    }

                    for( int k=0;k< inverMat.length;k++){
                        for(int i=0;i< inverMat[0].length;i++){
                            responseBuff.append(inverMat[k][i]+" ");
                        }
                        responseBuff.append("\n");
                    }

                    String response = responseBuff.toString();
                    System.out.println(response);
                    he.sendResponseHeaders(200, response.length());
                    OutputStream output = he.getResponseBody();
                    output.write(response.getBytes());
                    output.close();
                    he.close();
 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(he.getRequestMethod().equalsIgnoreCase("GET")){
                String response = "Hi there!";
                he.sendResponseHeaders(200, response.getBytes().length);//response code and length
                OutputStream os = he.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }


    static class FlattenHandler implements HttpHandler {
 
        @Override
        public void handle(HttpExchange he) throws IOException {

            System.out.println("Flatten [Return the matrix as a 1 line string, with values separated by commas.:");
            if (he.getRequestMethod().equalsIgnoreCase("POST")) {
                try {
                    List<List<Integer>> matrix = new ArrayList<>();
                    StringBuffer responseBuff = new StringBuffer();
                    
                    InputStream reqBody = he.getRequestBody();
                    matrix = parseCSVRequestData(reqBody);
                    
                    for(int i=0;i<matrix.size();i++ ){
                        List<Integer> temp =matrix.get(i);
                        for(int j=0;j<temp.size();j++ ){
                            if(i == matrix.size()-1 && j == temp.size() -1 )
                                responseBuff.append(temp.get(j));
                            else    
                                responseBuff.append(temp.get(j)+",");
                        }

                    }

                    String response = responseBuff.toString();
                    System.out.println(response);
                    he.sendResponseHeaders(200, response.length());
                    OutputStream output = he.getResponseBody();
                    output.write(response.getBytes());
                    output.close();
                    he.close();
 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(he.getRequestMethod().equalsIgnoreCase("GET")){
                String response = "Hi there!";
                he.sendResponseHeaders(200, response.getBytes().length);//response code and length
                OutputStream os = he.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class SumHandler implements HttpHandler {
 
        @Override
        public void handle(HttpExchange he) throws IOException {

            System.out.println("Sum [Return the sum of the integers in the matrix:");
            if (he.getRequestMethod().equalsIgnoreCase("POST")) {
                try {
                    List<List<Integer>> matrix = new ArrayList<>();
                    int resultSum =0;

                    InputStream reqBody = he.getRequestBody();
                    matrix = parseCSVRequestData(reqBody);

                    for(List<Integer> temp : matrix){
                        for(Integer val : temp){
                            resultSum += val;
                        }
                    }

                    String response = String.valueOf(resultSum);
                    System.out.println(response);
                    he.sendResponseHeaders(200, response.length());
                    OutputStream output = he.getResponseBody();
                    output.write(response.getBytes());
                    output.close();
                    he.close();
 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(he.getRequestMethod().equalsIgnoreCase("GET")){
                String response = "Hi there!";
                he.sendResponseHeaders(200, response.getBytes().length);//response code and length
                OutputStream os = he.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class MultiplyHandler implements HttpHandler {
 
        @Override
        public void handle(HttpExchange he) throws IOException {

            System.out.println("Multiply [Return the product of the integers in the matrix:");
            if (he.getRequestMethod().equalsIgnoreCase("POST")) {
                try {
                    List<List<Integer>> matrix = null;
                    int resultProduct =1;

                    InputStream reqBody = he.getRequestBody();
                    
                    matrix = parseCSVRequestData(reqBody);

                    for(List<Integer> temp : matrix){
                        for(Integer val : temp){
                            resultProduct *= val;
                        }
                    }

                    String response = String.valueOf(resultProduct);
                    System.out.println(response);
                    he.sendResponseHeaders(200, response.length());
                    OutputStream output = he.getResponseBody();
                    output.write(response.getBytes());
                    output.close();
                    he.close();
 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(he.getRequestMethod().equalsIgnoreCase("GET")){
                String response = "Hi there!";
                he.sendResponseHeaders(200, response.getBytes().length);//response code and length
                OutputStream os = he.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }


    public static List<List<Integer>> parseCSVRequestData(InputStream reqBody) {

        List<List<Integer>> result = new ArrayList<>();
        int cols =0;
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new InputStreamReader(reqBody, "UTF-8"));
        
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.size()>1)
                {
                cols = csvRecord.size();
                    for(int i=0;i<1;i++){
                        List<Integer> tempList = new ArrayList<>();
                        for(int j=0;j<cols;j++){
                            tempList.add(Integer.parseInt(csvRecord.get(j)));
                        }
                        result.add(tempList);
                    }
                }
            }
            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
