package jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jpashop.domain.item.Book;
import jpashop.domain.item.Item;
import jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	@GetMapping("/items/new")
	public String createForm(Model model) {
		model.addAttribute("form",new BookForm());
		return "items/createItemForm";
	}
	
	@PostMapping("/items/new")
	public String create(BookForm bookForm) {
		
		Book book = new Book();
		book.setName(bookForm.getName());
		book.setPrice(bookForm.getPrice());
		book.setStockQuantity(bookForm.getStockQuantity());
		book.setAuthor(bookForm.getAuthor());
		book.setIsbn(bookForm.getIsbn());
		
		itemService.saveItem(book);
		return "redirect:/items";
	}
	
	@GetMapping("/items")
	public String itemList(Model model) {
		List<Item> items = itemService.findItems();
		
		model.addAttribute("items",items);
		
		return "items/itemList";
	}
	
	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
		Book item =	(Book)itemService.findOne(itemId);
		
		BookForm form = new BookForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		form.setIsbn(item.getIsbn());
		form.setAuthor(item.getAuthor());
		
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}
	@PostMapping("/items/{itemId}/edit")
	public String updateItem(@ModelAttribute("form") BookForm form) {
		
		Book item = new Book();
		item.setId(form.getId());
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		item.setStockQuantity(form.getStockQuantity());
		item.setAuthor(form.getAuthor());
		item.setIsbn(form.getIsbn());
		
		itemService.saveItem(item);
		
		return "redirect:/items";
	}
}
