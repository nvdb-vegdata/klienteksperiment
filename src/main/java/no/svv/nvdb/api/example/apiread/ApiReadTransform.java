package no.svv.nvdb.api.example.apiread;

import no.svv.nvdb.api.example.apiread.data.Egenskap;
import no.svv.nvdb.api.example.apiread.data.VegObjekter;
import no.svv.nvdb.api.example.representation.Bridge;
import no.svv.nvdb.api.example.representation.Line;
import no.svv.nvdb.api.example.representation.Point;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Transforms objects from the domain model used in API Read to this applications internal domain model.
 * <p/>
 * User: Sigurd Stendal
 * Date: 13.02.15
 */
public class ApiReadTransform {

    public static Bridge toBridge(VegObjekter vegObjekt) {

        Bridge bridge = new Bridge();
        bridge.setObjektId(vegObjekt.getObjektId());
        bridge.getLines().addAll(toLines(vegObjekt.getLokasjon().getGeometriUtm33()));

        Optional<Egenskap> nameAttr = vegObjekt.getEgenskaper().stream().filter(e -> e.getNavn().equals("Navn")).findAny();
        if (nameAttr.isPresent()) {
            bridge.setName(nameAttr.get().getVerdi());
        }

        return bridge;
    }

    public static List<Line> toLines(String geometriUtm33) {
        String regex = "\\([^(^)]*\\)";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(geometriUtm33);

        List<Line> lines = new LinkedList<>();
        while (m.find()) {
            String numberString = geometriUtm33.substring(m.start(), m.end());
            lines.add(numberStringToLine(numberString));
        }

        return lines;

    }

    public static Line numberStringToLine(String numberLine) {

        List<String> pairs = Arrays.asList(numberLine.substring(1, numberLine.length() - 1).split(","));
        Line line = new Line();
        pairs.forEach(pair -> {
            String[] splitted = pair.trim().split(" ");
            line.getPoints().add(new Point(Double.parseDouble(splitted[0]), Double.parseDouble(splitted[1])));
        });
        return line;
    }

}
