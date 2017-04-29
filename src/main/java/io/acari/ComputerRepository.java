package io.acari;

import io.acari.simple.web_service.Computer;
import io.acari.simple.web_service.Cores;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ComputerRepository {
    private static final Map<String, Computer> modelToComputer = new HashMap<>();


    @PostConstruct
    public void setUp(){
        Computer sandwich = buildComputer(Cores.TWO, 16, "Razer", "Blade Stealth");
        modelToComputer.put(sandwich.getModel(), sandwich);
        Computer dellXps13 = buildComputer(Cores.TWO, 8, "Dell", "XPS 13");
        modelToComputer.put(dellXps13.getModel(),dellXps13);
        Computer bladePro = buildComputer(Cores.FOUR, 16, "Razer", "Blade Pro");
        modelToComputer.put(bladePro.getModel(),bladePro);
        Computer macPro = buildComputer(Cores.EIGHT, 16, "Apple", "Macbook Pro");
        modelToComputer.put(macPro.getModel(),macPro);
        Computer macAir = buildComputer(Cores.FOUR, 8, "Apple", "Macbook Air");
        modelToComputer.put(macAir.getModel(),macAir);
    }


    Collection<Computer> allComputers(){
        return modelToComputer.values();
    }

    Collection<Computer> computerByModel(String string){
        Objects.requireNonNull(string, "Model string cannot be null");
        Optional<Computer> computer = Optional.ofNullable(modelToComputer.get(string));
        return computer.isPresent() ? Collections.singleton(computer.get()) : Collections.emptySet();
    }

    private Computer buildComputer(Cores cores, int ram, String make, String model){
        Computer computer = new Computer();
        computer.setMake(make);
        computer.setModel(model);
        computer.setRam(ram);
        computer.setCores(cores);
        return computer;
    }
}
