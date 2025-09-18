package DDOS;
import java.net.*;
import java.util.*;

public class portScaning {

    public List<Integer> range(String host,int start,int end) { 
        List<Integer> openPorts = new ArrayList<>();
        for(int i=start; i<=end; i++) {
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(host,i),1000);
                openPorts.add(i);
            } catch (Exception e) {
                // port closed, ignore
            }
        }
        return openPorts;
    }

    public List<Integer> vunarable(String host) { 
        List<Integer> openPorts = new ArrayList<>();
        List<Integer> vulnerablePort = Arrays.asList(
            21, 22, 23, 25, 53, 67, 68, 69, 80, 110,
            111, 135, 139, 143, 161, 162, 389, 445,
            1433, 1521, 3389, 5900, 6667, 8080
        );

        for(int i : vulnerablePort) {
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(host,i),1000);
                openPorts.add(i);
            } catch (Exception e) {
                // port closed, ignore
            }
        }
        return openPorts;
    }

    public static void main(String args[]) {
        if(args.length < 2) {
            System.out.println("Usage: java portScaning <host> <r/v> [start end]");
            return;
        }

        List<Integer> openPorts = new ArrayList<>();
        portScaning p = new portScaning();

        if(args[1].equals("r")) {
            if(args.length < 4) {
                System.out.println("For range scan, provide start and end ports");
                return;
            }
            openPorts = p.range(args[0], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }

        if(args[1].equals("v")) {
            openPorts = p.vunarable(args[0]);
        }

        for(int i: openPorts)
            System.out.println("OpenPort: " + i);
    }
}
