package me.soulyana.demo.Repositories;

import me.soulyana.demo.Entities.Contact;
import org.springframework.data.repository.CrudRepository;

public interface AddressbookRepository extends CrudRepository<Contact, Long> {
}
