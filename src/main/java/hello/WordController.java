package hello;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {
    // TODO Implement the /words/{word} endpoint
	
	@GetMapping(path="/words/{word}")
	public Response getWords(@PathVariable(value="word", required=true) String words) {
		Response response=new Response();
		response.setWords(words);
		response.setPalindrome(words.equalsIgnoreCase(new StringBuffer(words).reverse().toString()));
		response.setAnagramOfPalindrome(anagramOfPalindrome(words));
		return response;
	}
	
	public boolean anagramOfPalindrome(String word)
    {
		int[] count = new int[128];
	     word.chars().mapToObj(e->(char)e).collect(Collectors.toList()).stream().forEach(o -> count[o.hashCode()]++);
	     return (Arrays.stream(count).boxed().collect(Collectors.toList()).stream().
	                filter(o -> o.intValue() % 2!=0).count() > 1 ? false : true);
    }
}
