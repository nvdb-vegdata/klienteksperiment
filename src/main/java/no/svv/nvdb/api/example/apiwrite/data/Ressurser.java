package no.svv.nvdb.api.example.apiwrite.data;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Sigurd Stendal
 * Date: 16.02.15
 */
public class Ressurser extends ArrayList<Ressurs> {
    @XmlElement(name = "ressurs")
    public List<Ressurs> getRessurser() {
        return this;
    }
}
