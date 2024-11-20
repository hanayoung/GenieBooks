package com.ssafy.cafe.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.service.CustomerService;
import com.ssafy.cafe.model.service.OrderService;
import com.ssafy.cafe.model.service.StampService;
import com.ssafy.cafe.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin("*")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);


    @Autowired
    UserService uService;

    @Autowired
    StampService sService;

    @Autowired
    OrderService oService;
    
    @PostMapping
    @Operation(summary = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. "
    	, description = "<pre> json중에서 id, name, pass만 입력해도 정상동작한다. \n"
    			+ "아래는 aa12 사용자를 추가하는 샘플코드\n "
    			+ "{\r\n"
    			+ "  \"id\": \"aa12\",\r\n"
    			+ "  \"name\": \"aa12\",\r\n"
    			+ "  \"pass\": \"aa12\",\r\n"
    			+ "}"
    			+ "</pre>")
    public Boolean insert(@RequestBody User user) {
    	logger.debug("user.insert", user);
    	int result = 0;
    	try {
    		result = uService.join(user);
    	}catch(Exception e) {
    		result = -1;
    	}
    	
        if(result == 1) {
            return true;
        }else {
            return false;
        }
    }
    
    @GetMapping("/isUsed")
    @Operation(summary = "request parameter로 전달된 id가 이미 사용중인지 반환한다.")
    public Boolean isUsedId(String id) {
        return uService.isUsedId(id);
    }


    @PostMapping("/login")
    @Operation(summary = "로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려보낸다."
    , description = "<pre>id와 pass 두개만 넘겨도 정상동작한다. \n 아래는 id, pass만 입력한 샘플코드\n"
    		+ "{\r\n"
    		+ "  \"id\": \"aa12\",\r\n"
    		+ "  \"pass\": \"aa12\"\r\n"
    		+ "}"
    		+ "</pre>")
    
    public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User selected = uService.login(user.getId(), user.getPass());
        if (selected != null) {
          Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getId(), "utf-8"));
//            Cookie cookie = new Cookie("loginId", selected.getId());
            cookie.setMaxAge(60*60*24*30); //30일
            response.addCookie(cookie);
        }
        return selected;
    }

    //이게 왜 필요할까????????? 주석처리함 : 2023.08.12    
    //TODO  :없애야 할 듯....
//    @GetMapping("/info")
//    @ApiOperation(value = "사용자의 정보와 함께 사용자의 주문 내역, 사용자 등급 정보를 반환한다.",
//    	notes = "6단계에서 사용됨.", response = Map.class)
//    public Map<String, Object> getInfo(String id) {
//        User selected = uService.selectUser(id);
//        if (selected == null) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("user", new User());
//            return map;
//        } else {
//            Map<String, Object> info = new HashMap<>();
//            info.put("user", selected);
//            List<Order> orders = oService.getOrdreByUser(selected.getId());
//            info.put("order", orders);
//            info.put("grade", getGrade(selected.getStamps()));
//            return info;
//        }
//    }

    // 위에 꺼 대신해서 이걸 만들었다. 
    // password를 sharedpreference에 저장하면 안되니, id만 받는데, 
    // 이 id와 쿠키에 있는 id가 같은지 확인해서 로그인 사용자를 조회해서 리턴함. 
    @GetMapping("/info")
    @Operation(summary = "사용자의 정보와 함께 사용자의 주문 내역, 사용자 등급 정보를 반환한다.",
    	description = "관통 6단계(Android app)에서 사용됨. 로그인 성공한 cookie 정보가 없으면 전체 null이 리턴됨")
    public Map<String, Object> getInfo(HttpServletRequest request, String id) {
  	    String idInCookie = "";
        Cookie [] cookies = request.getCookies();
        if(cookies != null) {
       	  for (Cookie cookie : cookies) {
  			try {
  			  if("loginId".equals(cookie.getName())){
  				  idInCookie = URLDecoder.decode(cookie.getValue(), "utf-8");
  				  System.out.println("value : "+URLDecoder.decode(cookie.getValue(), "utf-8"));
  			  }
  			} catch (UnsupportedEncodingException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		  }
        }
        
        User selected = uService.selectUser(id);

        if(!id.equals(idInCookie)) {
  		  logger.info("different cookie value : inputValue : {}, inCookie:{}", id, idInCookie);
  		  selected = null; // 사용자 정보 삭제.
  	    }else {
  		  logger.info("valid cookie value : inputValue : {}, inCookie:{}", id, idInCookie);
  	    }
  	  
  	    if (selected == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("user", new User());
            return map;
        } else {
            Map<String, Object> info = new HashMap<>();
            info.put("user", selected);
            List<Order> orders = oService.getOrderByUser(selected.getId());
            info.put("order", orders);
            info.put("grade", getGrade(selected.getStamps()));
            return info;
        }
    }
    
    
    @PostMapping("/info")
    @Operation(summary = "사용자의 정보와 함께 사용자의 주문 내역, 사용자 등급 정보를 반환한다.",
    description = "아래 User객체에서 id, pass 두개의 정보만 json으로 넘기면 정상동작한다.")
    public Map<String, Object> getInfo(@RequestBody User user) {
        User selected = uService.login(user.getId(), user.getPass());
        if (selected == null) {
            return null;
        } else {
            Map<String, Object> info = new HashMap<>();
            info.put("user", selected);
            List<Order> orders = oService.getOrderByUser(user.getId());
            info.put("order", orders);
            info.put("grade", getGrade(selected.getStamps()));
            return info;
        }
    }
    
//   기존 작성 로직인데, 갯수가 잘못 카운팅 되는 듯 해서 아래 calculateGrade 로직으로 변경. 2023.08.12    
    // 23.11.10 원복.
    public Map<String, Object> getGrade(Integer stamp) {
        Map<String, Object> grade = new HashMap<>();
        int pre = 0;
        for (Level level : levels) {
            if (level.max >= stamp) {
                grade.put("title", level.title);
                grade.put("img", level.img);
                if (!level.title.equals("커피나무")) {
                    int step = (stamp - pre) / level.unit + ((stamp - pre) % level.unit > 0 ? 1 : 0);
                    grade.put("step", step);
                    int to = level.unit - (stamp - pre) % level.unit;
                    grade.put("to", to);
                }
                grade.put("stepMax", level.unit);
                break;
            } else {
                pre = level.max;
            }
        }
        return grade;
    }
    

//	public Map<String, Object> calculateGrade(int stamp) {
//		
//        Map<String, Object> grade = new HashMap<>();
//
//		for(Level l : levels) {
//			if(l.max < stamp  ) {
//				if(l.max == Integer.MAX_VALUE) {
//					grade.put(l.title, l.img);
//					break;
//				}
//				else {
//					continue;
//				}
//				
//			}
//			else {
//				int steps = (stamp/l.unit)+1;
//				
//				int to = l.unit - stamp % l.unit;
//				grade.put("title", l.title);
//				grade.put("img", l.img);
//				grade.put("step", steps);
//				grade.put("to", to);
//				
//				break;
//			}
//		}

		
//		if(stamp>=350) {
//			grade.put("title", "커피나무");
//			grade.put("img", "coffee_tree.png");
//		}
//		else if(stamp>=225) {
//			grade.put("title", "커피콩");
//			grade.put("img", "coffee_beans.png");
//			grade.put("step", stamp/25);
//			grade.put("to", 25-(stamp%25));
//		}
//		else if(stamp>=125) {
//			grade.put("title", "열매");
//			grade.put("img", "coffee_fruit.png");
//			grade.put("step", stamp/20);
//			grade.put("to", 20-(stamp%20));
//		}
//		else if(stamp>=50) {
//			grade.put("title", "꽃");
//			grade.put("img", "flower.png");
//			grade.put("step", stamp/15);
//			grade.put("to", 15-(stamp%15));
//		}
//		else {
//			grade.put("title", "씨앗");
//			grade.put("img", "seeds.png");
//			grade.put("step", stamp/10);
//			grade.put("to", 10-(stamp%10));
//		}
//        
//		logger.info("stamp/10: {}, 10-(stamp%10) : {}", stamp/10, 10-(stamp%10));
//
//		int unit = -1;
//
//		if (stamp < 50) {
//			grade.put("title", "씨앗");
//			grade.put("img", "seeds.png");
//
//		} else if (stamp < 125) {
//			grade.put("title", "꽃");
//			grade.put("img", "flower.png");
//			stamp -= 50;
//			unit = 15;
//		} else if (stamp < 225) {
//			grade.put("title", "열매");
//			grade.put("img", "coffee_fruit.png");
//
//			stamp -= 125;
//			unit = 20;
//		} else if (stamp < 350) {
//			grade.put("title", "커피콩");
//			grade.put("img", "coffee_beans.png");
//
//			stamp -= 225;
//			unit = 25;
//		} else {
//			grade.put("title", "나무");
//			grade.put("img", "coffee_tree.png");
//		}
//
//		if (stamp < 350) {
//			grade.put("step", stamp / unit + 1);
//			grade.put("to", unit - stamp % unit);
//		}
//
//		
//		return grade;
//	}


    private List<Level> levels;

    @PostConstruct
    public void setup() {
        levels = new ArrayList<>();
        levels.add(new Level("씨앗", 10, 50, "seeds.png"));
        levels.add(new Level("꽃", 15, 125, "flower.png"));
        levels.add(new Level("열매", 20, 225, "coffee_fruit.png"));
        levels.add(new Level("커피콩", 25, 350, "coffee_beans.png"));
        levels.add(new Level("커피나무", Integer.MAX_VALUE, Integer.MAX_VALUE, "coffee_tree.png"));
    }



    class Level {
        String title;
        int unit;
        int max;
        String img;

        private Level(String title, int unit, int max, String img) {
            this.title = title;
            this.unit = unit;
            this.max = max;
            this.img = img;
        }
    }
}
