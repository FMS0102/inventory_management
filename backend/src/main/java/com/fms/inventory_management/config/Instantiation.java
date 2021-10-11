package com.fms.inventory_management.config;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fms.inventory_management.dto.TransactionDTO;
import com.fms.inventory_management.entities.CurrentInventory;
import com.fms.inventory_management.entities.InOperation;
import com.fms.inventory_management.entities.Product;
import com.fms.inventory_management.entities.User;
import com.fms.inventory_management.entities.enums.MaterialType;
import com.fms.inventory_management.entities.enums.UnitMeasurement;
import com.fms.inventory_management.repositories.CurrentInventoryRepository;
import com.fms.inventory_management.repositories.InOperationRepository;
import com.fms.inventory_management.repositories.ProductRepository;
import com.fms.inventory_management.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private InOperationRepository prodInputRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository prodRepository;

	@Autowired
	private CurrentInventoryRepository inventoryRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		prodInputRepo.deleteAll();
		userRepository.deleteAll();
		prodRepository.deleteAll();
		inventoryRepository.deleteAll();

		User felipe = new User(null, "Felipe", "felsimoes@gmail.com", "Suprimentos", "secret");
		userRepository.save(felipe);

		Product prod1 = new Product(8544L, "CIMENTO", UnitMeasurement.PCT, BigDecimal.valueOf(30.80),
				MaterialType.QUIMICO);
		Product prod2 = new Product(8545L, "CALL", UnitMeasurement.PCT, BigDecimal.valueOf(22.10),
				MaterialType.QUIMICO);

		prodRepository.saveAll(Arrays.asList(prod1, prod2));

		InOperation entrada = new InOperation(null, Instant.now(), "VV01", felipe);

		TransactionDTO ope1 = new TransactionDTO(BigDecimal.valueOf(38.00), BigDecimal.valueOf(36.50), prod1);
		TransactionDTO ope2 = new TransactionDTO(BigDecimal.valueOf(26.00), BigDecimal.valueOf(21.50), prod2);

		entrada.addProducts(ope1);
		entrada.addProducts(ope2);
		entrada.addProducts(ope2);// definir funÃ§Ã£o para somar a quantidade

		List<CurrentInventory> list = entrada.getTransactions().stream()
				.map((x) -> new CurrentInventory(x.getQuantity(), x.getPrice(), x.getProduct()))
				.collect(Collectors.toList());

		inventoryRepository.saveAll(list);
		prodInputRepo.save(entrada);
	}

}