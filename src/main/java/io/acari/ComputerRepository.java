package io.acari;

import com.google.common.collect.Sets;
import io.acari.simple.web_service.Computer;
import io.acari.simple.web_service.Cores;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.*;
import static java.util.Collections.*;

@Component
public class ComputerRepository {
    private static final Map<String, Set<Computer>> modelToComputer = new HashMap<>();


    @PostConstruct
    public void setUp(){
        Computer sandwich = buildComputer(Cores.TWO, 16, "Razer", "Blade", "Stealth");
        Computer bladePro = buildComputer(Cores.FOUR, 16, "Razer", "Blade", "Pro");
        modelToComputer.put(sandwich.getModel(), newHashSet(sandwich, bladePro));
        Computer dellXps13 = buildComputer(Cores.TWO, 8, "Dell", "XPS", "13");
        modelToComputer.put(dellXps13.getModel(), singleton(dellXps13));
        Computer macPro = buildComputer(Cores.EIGHT, 16, "Apple", "Macbook", "Pro");
        Computer macAir = buildComputer(Cores.FOUR, 8, "Apple", "Macbook", "Air");
        modelToComputer.put(macAir.getModel(), newHashSet(macAir, macPro));
    }


    Collection<Computer> allComputers(){
        return modelToComputer.entrySet().stream()
                .flatMap(stringSetEntry -> stringSetEntry.getValue().stream())
                .collect(Collectors.toSet());
    }

    Collection<Computer> computerByModel(String string){
        Objects.requireNonNull(string, "Model string cannot be null");
        return Optional.ofNullable(modelToComputer.get(string)).orElseGet(Collections::emptySet);
    }

    private Computer buildComputer(Cores cores, int ram, String make, String model, String subModel){
        Computer computer = new Computer();
        computer.setMake(make);
        computer.setModel(model);
        computer.setSubModel(subModel);
        computer.setRam(ram);
        computer.setCores(cores);
        return computer;
    }
}
