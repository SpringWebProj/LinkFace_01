package com.linkface.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.linkface.dto.UserDTO;
import com.linkface.entity.FoodItem;
import com.linkface.entity.FoodName;
import com.linkface.entity.RecipeGrade;
import com.linkface.entity.RecipeName;
import com.linkface.entity.UserData;
import com.linkface.security.AccessAccount;
import com.linkface.service.FoodIngredientsService;
import com.linkface.service.FoodItemsService;
import com.linkface.service.FoodNameService;
import com.linkface.service.FoodOrderService;
import com.linkface.service.RecipeGradeService;
import com.linkface.service.RecipeNameService;
import com.linkface.service.SanctionService;
import com.linkface.service.UserCRUDService;
import com.linkface.service.UserDataService;
import com.linkface.service.UserService;
import com.linkface.util.Aes256;
import com.linkface.util.DtoIteration;
import com.linkface.util.EmailHandler;
import com.linkface.util.SearchTrie;

import lombok.Setter;

@CrossOrigin("*")
@Controller
public class SampleController {
	private UserDTO userDTO;
	@Setter(onMethod_=@Autowired)
	private RecipeNameService service;
	@Setter(onMethod_=@Autowired)
	private UserDataService dataservice;
	@Setter(onMethod_=@Autowired)
	private FoodItemsService foodservice;
	
	@Setter(onMethod_ = @Autowired)
	private FoodNameService foodNameService;
	
	@Setter(onMethod_ = @Autowired)
	private FoodOrderService foodOrderService;
	
	@Setter(onMethod_ = @Autowired)
	private FoodIngredientsService foodIngredientsService;
	
	@Setter(onMethod_=@Autowired)
	private UserService userService;
	
	@Setter(onMethod_ = @Autowired)
	private UserCRUDService crudService;
	
	
	@Setter(onMethod_=@Autowired)
    private SessionRegistry sessionRegistry;
	
	@Setter(onMethod_=@Autowired)
	private RecipeGradeService recipeGradeService;
	
	@Setter(onMethod_=@Autowired)
	private SanctionService sanctionService;
	
	@GetMapping("/react/resp")
	public void reactResp(Model model) {
		model.addAttribute("resp", userDTO);
	}
	@PostMapping("/react/resp")
	public String reactResp(@RequestBody UserDTO resp) {
		System.out.println(resp.getId() + " " + resp.getPassword());
		userDTO = resp;
		return "redirect:/main";
	}
	
	@GetMapping("/react/register")
	public void reactRegister() {}
	

	@GetMapping("/homepage")
	public void homepage(Model model) {
		model.addAttribute("food", foodNameService.showAll());
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
			model.addAttribute("jjim", dataservice.getJJim(account.getUserInfo().getUserKey()));
		}
	}

	
	@GetMapping("/choice")
	public void choiceGet() {
		
	}
	
	@GetMapping("/chuchun")
	public void chuchunGet(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
			Long key = account.getUserInfo().getUserKey();
			List<FoodItem> fooditemlist = foodservice.getFoodItems(); 
		    List<String> fooditems = new ArrayList<String>();
		    List<Long> jjimlist = new ArrayList<>();
		    List<Long> recipeids = new ArrayList<Long>();
		    dataservice.getJJim(key).forEach(i->{
		    	if(i.getFoodingredients() != null) {
		    		fooditems.add(i.getFoodingredients());
		    	}
		    });
		    dataservice.getJJim(key).forEach(i->{
		    	if(i.getJjim() != null) {
			    	jjimlist.add(i.getJjim());	
			    }
		    });
		    fooditemlist.forEach(i->{
		    	fooditems.forEach(j->{
		    		if(i.getIrdntnm().equals(j)) {
		    			recipeids.add(i.getRecipeid());
		    		}
		    	});
		    });
		    List<RecipeName> recipelist = service.getList();
		    List<RecipeName> myrecipelist = new ArrayList<RecipeName>();
		    List<RecipeName> chuchunlist = new ArrayList<RecipeName>();
		    List<RecipeName> chuchun2list= new ArrayList<RecipeName>();
		    fooditems.forEach(i->System.out.println(i));
		    recipelist.forEach(i->{
		    	jjimlist.forEach(j->{
		    		if(i.getRecipeid()==j) {
		    			myrecipelist.add(i);
		    		}
		    	});
		    });
		    recipelist.forEach(i->{
		    	myrecipelist.forEach(j->{
		    		if(i.getTynm().equals(j.getTynm()))
		    		{
		    			chuchunlist.add(i);
		    		}
		    		
		    	});
		    });
		    recipelist.forEach(i->{
		    	recipeids.forEach(j->{
		    		if(i.getRecipeid()==j) {
		    			chuchun2list.add(i);
		    		}
		    	});
		    });
		    model.addAttribute("chuchun1", chuchunlist.stream().distinct().collect(Collectors.toList()));
		    model.addAttribute("chuchun2", chuchun2list.stream().distinct().collect(Collectors.toList()));
		}
	}
	@PostMapping("/choice")
	public void myfooditemPost(UserData data) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
		    Long key = account.getUserInfo().getUserKey();
		    data.setUserKey(key);
		    System.out.println(data.getFoodingredients());
		}
	}
	
	@GetMapping("/search")
	public void search(String search, Model model) {
		model.addAttribute("food", foodNameService.showList(search));
	}
	
	@GetMapping("/write")
	public void write(Model model) {
		model.addAttribute("food", foodNameService.showAll());
	}
	
	@GetMapping("/food")
	public void food(int id, Model model) {
		model.addAttribute("food", foodNameService.showOne(id));
		model.addAttribute("order", foodOrderService.showOne(id));
		model.addAttribute("ingredients", foodIngredientsService.showOne(id));
	}
	
	@GetMapping("/userpage")
	public void userpage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AccessAccount account = (AccessAccount) principal;
		System.out.println("account " + account.getUserInfo().getUserEmail());
		model.addAttribute("account", account);
	}
	
	@GetMapping("/sendkey")
	public String sendKey(RedirectAttributes rttr) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AccessAccount account = (AccessAccount) principal;
		rttr.addFlashAttribute("key", Aes256.encrypt(Long.toString(account.getUserInfo().getUserKey())));
		
		return "redirect:/newpassword";
	}
	
	@PostMapping("/modify")
	public String modify(UserDTO dto, RedirectAttributes rttr) { // 개인 정보 수정
		System.out.println(dto.getId() + " " + dto.getEmail() + " " + dto.getName() + " " + dto.getKey());
		if(crudService.modifyEmail(dto.getEmail(), dto.getKey())) {
			crudService.modifyUpdateDate(dto.getKey());
			userService.checkSessionAndUpdate(dto.getKey());
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			AccessAccount account = (AccessAccount) principal;
			EmailHandler.authLinkSendEmail("email", dto.getKey(), dto.getEmail(), account.getUserStatus().getAuthToken());
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/main";
	}
	
	@PostMapping("/wishlist")
	public ResponseEntity<String> wishlist(@RequestBody UserData userData) {
		System.out.println(userData);
		
		String result = "";
		if(dataservice.getOne(userData) == null) {
			dataservice.insertJJim(userData);
			result = "insert";
		}
		else {
			dataservice.deleteJJim(userData);
			result = "delete";
		}
		userService.checkSessionAndUpdate(userData.getUserKey());
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping("/searchtest")
	public void tests() {
		
	}
	
	// 메인
	@GetMapping(value={"","/main"})
	public String main(Model model) throws UnsupportedEncodingException {
		model.addAttribute("food", foodNameService.showAll());
		return "main";
	}
	
	// 회원
	@GetMapping("/member")
	public void member() {
		Object getPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("principal " + getPrincipal);
		AccessAccount account = (AccessAccount) getPrincipal;
		System.out.println(account.getUserStatus().getRole());
		
		List<AccessAccount>principals = sessionRegistry.getAllPrincipals()
                .stream().map(o -> (AccessAccount) o).collect(Collectors.toList());
		for(AccessAccount principalQ: principals) {
			System.out.println(principalQ.getUserInfo().getUserKey());
		}
		
		EmailHandler.sendEmail(account.getUserInfo().getUserEmail(), "제목", "내용");
	}
	
	// 관리자
	@GetMapping("/admin")
	public void admin() {
		
	}
	
	// 로그인
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 회원가입
	@GetMapping("/signup")
	public void signup() {
		
	}
	// ID EMAIL 중복 체크
	@PostMapping("/duplicatecheck")
	public ResponseEntity<Boolean> Check(@RequestBody UserDTO checkUser) {

		// id 값이 있다면 id 검사 / null 이라면 email 검사
		// duplicateCheck 로직 수정 필요
		return new ResponseEntity<>
			(DtoIteration.userDTOCheckNonNull(checkUser, "id")  ?
					  userService.duplicateCheck(checkUser.getId(),"id")
					: userService.duplicateCheck(checkUser.getEmail(),"email"),HttpStatus.OK);
	}
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> signupPost(@RequestBody UserDTO newUser) {
	
		// 상태값
		boolean status = true;
		// 백엔드에서 다시 중복체크와 패스워드 검증 
		status = userService.duplicateCheck(newUser.getId(),"id");
		status = userService.duplicateCheck(newUser.getEmail(),"email");
		status = newUser.getPassword().equals(newUser.getPasswordCheck());
		// DB 생성 및 초기 이메일 발송
		status = userService.registUser(newUser);

		// 필요하다면 추후 추가
		
		// 위 로직 중 하나라도 실패할 경우 오류 반환
		return status ? 
				new ResponseEntity<>(newUser.getId(),HttpStatus.OK) :
				new ResponseEntity<>("BAD REQUEST",HttpStatus.BAD_REQUEST);
	}
	
	// 이메일 인증 , 이메일 변경 , 패스워드 재발급 인증
	@GetMapping("/certification/{urlText}")
	public String certification(@PathVariable String urlText, Model model) throws Exception {
		
	    String message = "";
	    
		Map<String,String> data = EmailHandler.analysisUrl(urlText);

		// 객체가 비어있거나 데이터 타입이 email 또는 password 가 아니라면 올바른 데이터가 아님
		if(data.isEmpty()){
			message = "처리 중 오류가 발생했습니다. 인증 링크를 재요청해주세요";
		}
		// 이메일 인증
		else if(data.get("type").equals("email")) {
			message = userService.emailConformity
					(Long.parseLong(data.get("key")),data.get("email"),data.get("token"));
		}
		// 패스워드 변경이며 토큰값이 유효할 경우
		else if(data.get("type").equals("password") && userService.userTokenCheck(
				Long.parseLong(data.get("key")),data.get("token"))) {
			// 키를 담아서 전달
			model.addAttribute("key", Aes256.encrypt(data.get("key")));
			return "newpassword";
		}
		else {
			message = "유효하지 않은 인증링크입니다. 인증 링크를 재요청해주세요";
		}
		
		model.addAttribute("message", message);
		return "certification";
	}
	// 아이디 비밀번호 찾기
	@PostMapping(value = "/find", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> findPost(@RequestBody Map<String,UserDTO> checkObj) {
	
		String type = checkObj.keySet().iterator().next();
		
		String message = "";
		
		boolean status = true;
		
		// 키가 id password 에 해당하며 객체가 존재할 경우 true
		status = (type.equals("id") || type.equals("password")) && checkObj.get(type) != null;
		// 객체 내부 필요한 값들이 null 이 아니라면 true
		status = type.equals("password") ? 
					DtoIteration.userDTOCheckNonNull(checkObj.get(type), "email","name","id") :
					DtoIteration.userDTOCheckNonNull(checkObj.get(type), "email","name");
		
		message = userService.findUser(checkObj.get(type), type);
		
		if(!status) { message = "해당하는 사용자가 존재하지 않습니다"; }

		System.out.println(message);
		return status ? 
				new ResponseEntity<>(message,HttpStatus.OK) :
				new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	// 패스워드 변경
	@GetMapping("/newpassword")
	public void newPassword() {
		
	}
	@PostMapping(value = "/newpassword", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> newPasswordPost(@RequestBody Map<String,String> passwordData) {
	
		
		Long key = Long.parseLong(Aes256.decrypt(passwordData.get("key")));

		if(userService.duplicateCheck(key, "key")) {
			return new ResponseEntity<>("유효하지 않은 사용자입니다",HttpStatus.BAD_REQUEST);
		}
		else if(!passwordData.get("password").equals(passwordData.get("passwordCheck"))) {
			return new ResponseEntity<>("패스워드가 일치하지 않습니다",HttpStatus.BAD_REQUEST);
		}
		
		// 세션 제거
		List<AccessAccount>principals = sessionRegistry.getAllPrincipals()
		                .stream().map(o -> (AccessAccount) o).collect(Collectors.toList());
		for(AccessAccount principal  : principals) {
			if(principal.getUserInfo().getUserKey() == key) {
				for(SessionInformation s : sessionRegistry.getAllSessions(principal, false)) {
						s.expireNow();
				}
			}
		}
		
		userService.createNewPassword(key,passwordData.get("password"));
		return new ResponseEntity<>("패스워드 변경이 완료되었습니다. 로그인을 해주세요",HttpStatus.OK);
	}
	
	// 별점 컨트롤러
	@PostMapping(value = "/recipegrade", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> recipegrade(@RequestBody RecipeGrade recipeGrade){
		
		String message = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
			recipeGrade.setUserKey(account.getUserInfo().getUserKey());
			message = recipeGradeService.recipeGradeCUD(recipeGrade);
		}
		else {
			message = "평점 등록에 실패하셨습니다";
		}

		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	// 권한없는 사용자
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}

	// 카테고리별 조회수 리스트 반환 & 게시글 작성 상위 5명 반환
	@PostMapping(value = "/chartdata", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, List<Object>>> chartDataReturnController(@RequestBody Map<String,String> dayAndType){

		return dayAndType.get("type").equals("categoryViewsCount") ? 
				new ResponseEntity<>(foodNameService.getViewsbyCategory(dayAndType.get("day")),HttpStatus.OK) :
				new ResponseEntity<>(foodNameService.getMostActiveUsers(dayAndType.get("day")),HttpStatus.OK);
	}
	
	// 아이디 검색
	@PostMapping(value = "/autocompleteuserid", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> searchUserController(@RequestBody String id){

		List<String> data = SearchTrie.searchAutocompleteData(id.trim());
		if(data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else if(data.size() > 5) {
			List<String> dataSizeFive = new ArrayList<>();
			for(int i = 0; i < 5; i++) {
				dataSizeFive.add(data.get(i));	
			}
			return new ResponseEntity<>(dataSizeFive,HttpStatus.OK);
		}
		return new ResponseEntity<>(data,HttpStatus.OK);
				
	}
	
	// 유저 정보
	@PostMapping(value = "/userinfo", produces = "application/json; charset=UTF-8")
	public ResponseEntity<UserDTO> getUserInfoController(@RequestBody String id){
				
		UserDTO user = userService.adminLookupUserinfo(id);
		return user == null ?
			new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
			new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping(value = "/admindeleterecipe", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> adminDeleteRecipeController(@RequestBody Map<String,String> deleteRecipe){
		
		FoodName name = foodNameService.showOne(Integer.parseInt(deleteRecipe.get("recipeId")));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AccessAccount account = (AccessAccount) principal;
		if(deleteRecipe.get("reasons").trim().equals("")) {
			return new ResponseEntity<>("사유는 필수입력입니다.",HttpStatus.BAD_REQUEST);
		}
		else if(name == null) {
			return new ResponseEntity<>("알 수 없는 게시글입니다.",HttpStatus.BAD_REQUEST);
		}
		else if(!userService.checkUserRole(account.getUserInfo().getUserKey(), name.getUserKey())) {
			return new ResponseEntity<>("해당 게시물을 삭제할 권한이 없습니다.",HttpStatus.BAD_REQUEST);
		}
		// 게시글 삭제
		foodNameService.deleteRecipe(name.getRECIPEID());
		// 제제사유 추가
		sanctionService.sanctionsRecipe(name.getUserKey(), name.getRECIPEID(),
				deleteRecipe.get("reasons"), account.getUserInfo().getUserKey());
		// 메시지 발송 로직 (2)
		
		return new ResponseEntity<>("게시글이 삭제되었습니다",HttpStatus.OK);
		
	}
	@PostMapping(value = "/adminsanctionsuser", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> adminSanctionsUserController(@RequestBody Map<String,String> sanctionsUser){
		
		UserDTO user = userService.getUser(sanctionsUser.get("id"));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AccessAccount account = (AccessAccount) principal;
		int day;
		switch(sanctionsUser.get("day")){
	        case "day": 
	        	day = 3;
	        	break;
	        case "week":
	            day = 7;
	            break;
	        case "month" :
	            day = 30;
	            break;
	        case "permanence" :
	            day = 7300;
	            break;
	        default :
	            day = 0;
	    }
		if(day == 0) {
			return new ResponseEntity<>("잘못된 접근입니다.",HttpStatus.BAD_REQUEST);
		}
		else if(user == null) {
			return new ResponseEntity<>("알 수 없는 사용자입니다.",HttpStatus.BAD_REQUEST);
		}
		else if(!userService.checkUserRole(account.getUserInfo().getUserKey(), user.getKey())) {
			return new ResponseEntity<>("처리할 수 없는 요청입니다.",HttpStatus.BAD_REQUEST);
		}
		userService.sanctionUser(user.getKey(), day, false);
		
		sanctionService.sanctionsUser(user.getKey(),
				sanctionsUser.get("reasons"), account.getUserInfo().getUserKey());
		
		List<AccessAccount>principals = sessionRegistry.getAllPrincipals()
                .stream().map(o -> (AccessAccount) o).collect(Collectors.toList());
		for(AccessAccount accessAccount  : principals) {
			if(accessAccount.getUserInfo().getUserKey() == user.getKey()) {
				for(SessionInformation s : sessionRegistry.getAllSessions(accessAccount, false)) {
						s.expireNow();
				}
			}
		}
		
		return new ResponseEntity<String>("완료되었습니다.",HttpStatus.OK);
		
	}
	@PostMapping(value = "/changeuserrole", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> changeUserRoleController(@RequestBody Map<String,String> roleChangeUser){

		UserDTO user = userService.getUser(roleChangeUser.get("id"));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AccessAccount account = (AccessAccount) principal;
		String role = "ROLE_";
		
		if(user == null) {
			return new ResponseEntity<>("알 수 없는 사용자입니다.",HttpStatus.BAD_REQUEST);
		}
		else if(!roleChangeUser.get("role").equals("MEMBER") && 
				!roleChangeUser.get("role").equals("MANAGER")) {
			return new ResponseEntity<>("잘못된 접근입니다.",HttpStatus.BAD_REQUEST);
		}
		else if(!userService.checkUserRole(account.getUserInfo().getUserKey(), user.getKey())) {
			return new ResponseEntity<>("처리할 수 없는 요청입니다.",HttpStatus.BAD_REQUEST);
		}

		role += roleChangeUser.get("role");
		userService.changeUserRole(user.getKey(), role);
		
		List<AccessAccount>principals = sessionRegistry.getAllPrincipals()
                .stream().map(o -> (AccessAccount) o).collect(Collectors.toList());
		for(AccessAccount accessAccount  : principals) {
			if(accessAccount.getUserInfo().getUserKey() == user.getKey()) {
				for(SessionInformation s : sessionRegistry.getAllSessions(accessAccount, false)) {
						s.expireNow();
				}
			}
		}
		
		return new ResponseEntity<>("권한이 변경되었습니다",HttpStatus.OK);
		
	}
}
