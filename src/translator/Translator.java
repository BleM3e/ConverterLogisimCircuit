package translator;

import java.io.File;

import core.Core;

public class Translator {
    Core filtered_circuit;

    public Translator(File file) throws Exception {
        filtered_circuit = new Core(file);
    }
}
