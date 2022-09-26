package jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.item.Item;
import jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findItem(itemId);
	}
	
}
