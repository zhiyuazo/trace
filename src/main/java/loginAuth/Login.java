package loginAuth;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.json.JSONUtil;

@Controller
public class Login {
	public static ClassPathResource csv_file = new ClassPathResource("users.csv");
	public static CsvReader csv_reader = CsvUtil.getReader();
	public static CsvData data = csv_reader.read(csv_file.getFile());
	public static HashMap<String, User> authUser = new HashMap<>();
	static {
		List<CsvRow> csvrows = data.getRows();
		for (CsvRow row : csvrows) {
		    List<String> userItem = row.getRawList();
		    User tmp =  new User();
		    tmp.setName(userItem.get(0));
		    tmp.setPassword(userItem.get(1));
		    authUser.put(tmp.getName(), tmp);
		}
	}
	
	@PostMapping("/login")
	@ResponseBody
    public int login(@RequestBody User Input_User,HttpSession session) {
		String name = Input_User.getName() ; 
		String pwd = Input_User.getPassword();
		Console.log(name + ":" +pwd );
		if(authUser.containsKey(name)) {
			if(authUser.get(name).getPassword().equals(pwd)) {
		        session.setAttribute("user", Input_User);
				System.out.println("@login: " + Input_User);
				return 0;
			}else {
				return 1;
			}
		}else {
	        return 1;
		}
        
    }
}
