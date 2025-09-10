import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        @GetMapping(path = "/get_ping/{ip}")
        public ResponseEntity<String> getIp(@PathVariable("ip") String ip) {
            List<String> commands = new ArrayList<String>();
            commands.add("ping");
            commands.add(ip);
            ProcessBuilder build = new ProcessBuilder(commands);
            String row;
            int x = 0;
            List<String> packet = new ArrayList<String>();
            List<Error> exceptions= new ArrayList<Error>();
            try {
                Process process = build.start();
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                for (int i = 0; (row = input.readLine()) != null; i++) {
                    packet.add(row);
                }
                if (packet.size() == 11) {
                    packet = packet.subList(7, 11);
                }
                row = null;
                input.close();

                for (int i = 0; (row = error.readLine()) != null; i++) {
                    exceptions.add(new Error(row));
                }
                if (exceptions.size() > 0 ) {
                    System.out.println(exceptions);
                }
                row = null;
                error.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<String>(new Gson().toJson(String.join("", packet)), HttpStatus.OK);
        }
    }
}