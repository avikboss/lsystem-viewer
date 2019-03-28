import java.util.HashMap;

public class LSystem {

    private String axiom;
    private HashMap<Character,String> rules;
    private double angle;
    private double dist;
    private int iterations;

    public LSystem(String axiom, HashMap<Character, String> rules,int iterations,double angle) {
        this.axiom = axiom;
        this.rules = rules;
        this.angle = angle;
        this.dist = 10;
        this.iterations = iterations;
    }

    public LSystem() {
        this.axiom = "F--F--F";
        this.angle = 60;
        this.dist = 10;
        this.iterations = 1;
        this.rules = new HashMap<>();
        this.rules.put('F',"F+F--F+F");

    }

    public String iterate() {
        String a = axiom;
        StringBuilder result = new StringBuilder();
        for (int i = 0;i<iterations;i++) {
            for (int j=0;j<a.length();j++) {
                String rule = rules.get(a.charAt(j));
                if (rule != null) {
                    result.append(rule);
                } else {
                    result.append(a.charAt(j));
                }
            }
            a = result.toString();
            result.setLength(0);
        }
        System.out.println(a);
        return a;
    }

    public String getAxiom() {
        return axiom;
    }

    public void setAxiom(String axiom) {
        this.axiom = axiom;
    }

    public HashMap<Character, String> getRules() {
        return rules;
    }

    public void setRules(HashMap<Character, String> rules) {
        this.rules = rules;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public static LSystem fromText(String text) {
        String[] split = text.split(";");
        LSystem system = new LSystem();
        HashMap<Character,String> rules = new HashMap<>();

        system.setDist(Double.valueOf(split[0]));
        system.setAngle(Double.valueOf(split[1]));
        system.setIterations(Integer.valueOf(split[2]));
        system.setAxiom(split[3]);

        for (int i = 4;i<split.length;i++) {
            String[] rule = split[i].split("=");
            rules.put(rule[0].charAt(0),rule[1]);
        }
        system.setRules(rules);

        return system;
    }

}
