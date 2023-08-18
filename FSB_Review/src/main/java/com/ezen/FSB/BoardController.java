package com.ezen.FSB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.FSB.dto.AlarmDTO;
import com.ezen.FSB.dto.BoardDTO;
import com.ezen.FSB.dto.BoardFilesDTO;
import com.ezen.FSB.dto.Board_replyDTO;
import com.ezen.FSB.dto.MemberDTO;
import com.ezen.FSB.dto.NoticeDTO;
import com.ezen.FSB.dto.ReportDTO;
import com.ezen.FSB.service.AlarmMapper;
import com.ezen.FSB.service.BoardMapper;
import com.ezen.FSB.service.MemberMapper;
import com.ezen.FSB.service.MypageMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper boardMapper;
	
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MypageMapper myPageMapper;
	
	@Autowired
	AlarmMapper alarmMapper;
	
	@Resource(name = "uploadPath")
	private String upPath;
	
	//mode로 자유게시판,익명게시판, 중고게시판 나누었음
	//자유게시판 mode = ""
	//익명게시판 mode = "anony"
	//중고게시판 ( 나중에 컨트롤러만 분리했음 ) mode = "sh"
	
	//자유게시판 리스트 
	@RequestMapping("/board_free.do")
	public ModelAndView board_free_list(HttpServletRequest req, java.util.Map<String, Integer> params) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("upPath", session.getServletContext().getRealPath("resources/images"));


		//공지사항 리스트
		String mode = req.getParameter("mode");
		//mode로 게시판 마다 다른 공지사항 가져옴
		
		List<NoticeDTO> nlist =boardMapper.nlistBoard(mode);
		
		//조회수 순 (자유게시판)
		List<BoardDTO> readlist = boardMapper.readlist();
		mav.addObject("readlist", readlist);
		//댓글순
		List<BoardDTO> replylist = boardMapper.replylist();
		mav.addObject("replylist", replylist);
			//페이지 넘버
		int pageSize = 10; // 한페이지에 글 몇개 들어가는지.
		
		//게시글 목록 쪽수
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지 
		int startRow = (currentPage - 1) * pageSize + 1; 
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountBoard();
		params.put("start", startRow);
		params.put("end", endRow);

		if (endRow > count)
			endRow = count;
		List<BoardDTO> list = null;
		if (count > 0) {
			list = boardMapper.listBoard(params);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			//삼항 조건문 true면 0 false면 1
			
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.addObject("count", count);
		mav.addObject("nlistBoard", nlist);
		mav.addObject("listBoard", list);
		mav.setViewName("board/list_free");
		return mav;
	}
	

	//익명게시판 리스트 
	@RequestMapping("/board_anony.do")
	public ModelAndView boardAnony(HttpServletRequest req, java.util.Map<String, Integer> params) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("upPath", session.getServletContext().getRealPath("resources/images"));
		
		//공지사항 리스트 
				String mode = req.getParameter("mode");
				List<NoticeDTO> nlist =boardMapper.nlistBoard(mode);
		//조회수 순
				List<BoardDTO> readlist = boardMapper.readlist_a();
				mav.addObject("readlist", readlist);
		//댓글순
				List<BoardDTO> replylist = boardMapper.replylist_a();
				mav.addObject("replylist", replylist);
		//페이지 넘버
		int pageSize = 10;
 
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountBoard_anony();
		
		params.put("start", startRow);
		params.put("end", endRow);
		params.put("mode", 1);
		
		if (endRow > count)
			endRow = count;
		List<BoardDTO> list = null;

		if (count > 0) {
			list =boardMapper.listBoard(params);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		mav.addObject("count", count);
		mav.addObject("listBoard", list);
		mav.addObject("nlistBoard", nlist);
		mav.setViewName("board/list_anony");
		return mav;
	}
	//자유게시판 작성, 익명 게시판 글 작성 폼 띄우기
	@RequestMapping(value = "/write_board.do", method = RequestMethod.GET)
	public ModelAndView writeFormBoard(HttpServletRequest req, String mode) {
		int num = 0, re_group = 0, re_step = 0, re_level = 0;
		ModelAndView mav = new ModelAndView();
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		String snum = req.getParameter("board_num");
		
		if (snum != null) { // 만약 넘어온 board_num 있으면 새글이 아니라 답글임.
			num = Integer.parseInt(snum);
			re_group = Integer.parseInt(req.getParameter("board_re_group"));
			re_step = Integer.parseInt(req.getParameter("board_re_step"));
			re_level = Integer.parseInt(req.getParameter("board_re_level"));
		}
		
		mav.addObject("board_num", num);
		mav.addObject("mem_num", mdto.getMem_num());
		mav.addObject("board_re_group", re_group);
		mav.addObject("board_re_step", re_step);
		mav.addObject("board_re_level", re_level);
		mav.addObject("mode", mode);
		
		mav.setViewName("board/writeForm");
		return mav;
	}
	//자유 익명게시판 글작성 
		@RequestMapping(value = "/write_board.do", method = RequestMethod.POST)
		public String writeProBoard(HttpServletRequest req, String mode, @RequestParam("filename") List<MultipartFile> multiFileList,@ModelAttribute BoardDTO dto, BindingResult result)
				throws IllegalStateException, IOException {
			if (result.hasErrors()) {
				dto.setBoard_img1("");
				dto.setBoard_img2("");
				dto.setBoard_img3("");
				dto.setBoard_img4("");
			}
			//로그인 체크 
			HttpSession session= req.getSession();
				MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
				if(mdto == null) {
					session.invalidate();
					req.setAttribute("msg","로그인 해주세요" );
					req.setAttribute("url","login.do" );
					return "message";
				}
			if(mode.equals("anony")) { //익명게시판에서 폼을 보냈을 경우 익명 셋팅
				dto.setBoard_anony_check(1);
				req.setAttribute("board_anony_check",dto.getBoard_anony_check());
			}
			req.setAttribute("mode", "all");
			dto.setBoard_ip(req.getRemoteAddr()); // ip 받아서 set
			
			//이미지 받기
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req; // multipart로 사진받기
			MultipartFile mf = mr.getFile("board_img1");
			MultipartFile mf2 = mr.getFile("board_img2");
			MultipartFile mf3 = mr.getFile("board_img3");
			MultipartFile mf4 = mr.getFile("board_img4");
			
			String board_img1 = mf.getOriginalFilename(); //사진 받아온 이름
			String board_img2 = mf2.getOriginalFilename();
			String board_img3 = mf3.getOriginalFilename();
			String board_img4 = mf4.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID(); //이미지 중복 시 엑스박스 방지용 랜덤 파일명

			String upPath = session.getServletContext().getRealPath("/resources/img"); // 이미지용 upPath
			String upPath1 = session.getServletContext().getRealPath("/resources/files"); // 파일 업로드 upPath1
			session.setAttribute("upPath1", upPath1);
			session.setAttribute("upPath", upPath);
			
			MemberDTO dto3 = (MemberDTO)session.getAttribute("login_mem");
			 dto.setMem_num(dto3.getMem_num());
			BoardFilesDTO fdto = new BoardFilesDTO();

			if (!mf.isEmpty()) { // 이미지 첨부 되어있을 시
				board_img1 = uuid.toString() + "_" + board_img1;
				File file1 = new File(upPath, board_img1);
				mf.transferTo(file1);
				dto.setBoard_img1(board_img1);
				
			} else if (mf.isEmpty()) { // 이미지 첨부 안되어있을시
				dto.setBoard_img1("");
			}
				
			 if (!mf2.isEmpty()) {
				board_img2 = uuid.toString() + "_" + board_img2; // 랜덤 파일명 붙여주기 
				File file2 = new File(upPath, board_img2);
				mf2.transferTo(file2); // 이미지 올려주기
				dto.setBoard_img2(board_img2);
				
			} else if (mf2.isEmpty()) {
				dto.setBoard_img2("");
			}
			if (!mf3.isEmpty()) {
				board_img3 = uuid.toString() + "_" + board_img3;
				File file3 = new File(upPath, board_img3);
				mf3.transferTo(file3);
				dto.setBoard_img3(board_img3);
			} else if (mf3.isEmpty()) {
				dto.setBoard_img3("");
			}
			
			if (!mf4.isEmpty()) {
				board_img4 = uuid.toString() + "_" + board_img4;
				File file4 = new File(upPath, board_img4);
				mf4.transferTo(file4);
				dto.setBoard_img4(board_img4);
			} else {
				dto.setBoard_img4("");
			}
//			
//			if(dto.getBoard_img1().equals("")){
//				dto.setBoard_img1(board_img2);
//				dto.setBoard_img2(board_img3);
//				dto.setBoard_img3(board_img4);
//			}
			
			req.setAttribute("dto", dto);
			
			int res = boardMapper.insertBoard(dto);
			if (res > 0) {
				  File fileCheck = new File(upPath1); // 업로드 된 파일 check
			         if (!fileCheck.exists()) // 만약 파일이 없다면, 
			            fileCheck.mkdirs(); //상위 디렉토리 만들어주기
			         List<Map<String, String>> fileList = new ArrayList<>();
			         if(multiFileList == null || multiFileList.get(0).getOriginalFilename().equals("")) { // writeform에서 다중파일 담아서 매개변수로 받아옴.
			        	 // 만약 파일 없으면 그냥 게시글 등록
			        	 if(mode.equals("anony")) {
			 				req.setAttribute("msg", "게시글 등록 성공");
			 				req.setAttribute("url", "board_anony.do?mode=anony");
			 			}else {
			 			req.setAttribute("msg", "게시글 등록 성공");
			 			req.setAttribute("url", "board_free.do?mode=");
			             return "message";
			 			}
			         }else { // 만약 파일이 있다면 
			         for (int i = 0; i < multiFileList.size(); i++) { //for문으로 파일 이름 받아오기, 사이즈 받아오기
			            String originFile = multiFileList.get(i).getOriginalFilename();
			            int filesize = (int) multiFileList.get(i).getSize();
			            String ext = originFile.substring(originFile.lastIndexOf(".")); //원래이름 확장자 앞에서 자르기 
			            String changeFile = UUID.randomUUID().toString() + ext;// 원래이름에 새로운 랜덤이름 붙여주기
			            fdto.setFilename(originFile); //원래 파일이름
			            fdto.setSavename(changeFile);//db에 저장될 새로운 파일이름 
			            fdto.setFilesize(filesize);
			            int board_num = boardMapper.maxRe_group(); // 최신글번호 가져오기 (방금 등록처리된  글)
			          	fdto.setBoard_num(board_num);
			            int res2 = boardMapper.fileInsert(fdto);
			            Map<String, String> map = new HashMap<>();
			            map.put("filename", originFile);
			            map.put("savename", changeFile);
			            fileList.add(map);
			         }
			        }
			         // 파일 업로드
			         try {
			        	 if (fileList.size()!=0) {
			            for (int i = 0; i < multiFileList.size(); i++) {
			               File uploadFile = new File(upPath1 + "\\" + fileList.get(i).get("savename"));
			               multiFileList.get(i).transferTo(uploadFile); //i번째 파일 꺼낸다음에 업로드 해주기.
			            }
			        	 }
			            System.out.println("다중 파일 업로드 성공");
			         } catch (IllegalStateException | IOException e) {
			            System.out.println("다중파일 업로드 실패 ㅠㅠ");
			            for (int i = 0; i < multiFileList.size(); i++) {
			               new File(upPath1 + "\\" + fileList.get(i).get("savename")).delete();
			               //위에서 만들어준 저장명 지워주기
			            }
			            e.printStackTrace();
			         }
				if(mode.equals("anony")) {
					req.setAttribute("msg", "게시글 등록 성공");
					req.setAttribute("url", "board_anony.do?mode=anony");
				}else {
				req.setAttribute("msg", "게시글 등록 성공");
				req.setAttribute("url", "board_free.do?mode=");
				}
			}else {
				req.setAttribute("msg", "게시글 등록 실패");
				req.setAttribute("url", "write_board.do");
			}
			return "message";
		}
		
		
	//파일 다운로드 처리
	@RequestMapping("/file_download.do")
	public ModelAndView fileDown(HttpServletRequest req,HttpServletResponse resp,@RequestParam int board_num) {
		ModelAndView mav = new ModelAndView("message"); 
		List<BoardFilesDTO> list = boardMapper.getFiles(board_num);
		 
		HttpSession session=req.getSession();
		//로그인 체크 
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) { // 로그인 되어있는지 체크
				session.invalidate(); 
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		String upPath1 = session.getServletContext().getRealPath("/resources/files");
			session.setAttribute("upPath1", upPath1);
			req.setAttribute("upPath1", upPath1);
			
			String name = req.getParameter("filename");
			 try {
		            String original_name = null;
		            String save_name = null;
		      
		            for(BoardFilesDTO fdto : list) { // 같은 이름의 파일이 있는지 확인
		               if(fdto.getFilename().equals(name)){
		                  original_name = fdto.getFilename();
		                  save_name = fdto.getSavename();
		               }
		            }
		            //파일 다운로드 부분은 저도 조금 더 공부하고 주석 달게요 .. 
		            original_name = new String(original_name.getBytes("UTF-8"), "iso-8859-1"); 
		            File file = new File(session.getServletContext().getRealPath("/resources/files/"+save_name));
		            FileInputStream fis = new FileInputStream(file); // 파일 읽기 
		               ServletOutputStream sos = resp.getOutputStream();
		               
		               resp.setContentType("application/octet-stream");
		               resp.setContentLength((int) file.length());
		               resp.setHeader("Content-Disposition", "attachment;filename=\""+ original_name +"\"");
		         
		               byte[] buffer = new byte[4096]; 
		               int bytesRead;
		               while ((bytesRead = fis.read(buffer)) != -1) {
		                   sos.write(buffer, 0, bytesRead);
		               }
		               fis.close();
		               sos.close();
		         } catch (IOException e) {
		            e.printStackTrace();
		         }
			 return mav;
	}
	//공지사항 상세보기
	
	@RequestMapping("/board_noti_content.do") 
	public ModelAndView boardNotiContent(HttpServletRequest req,@RequestParam int n_num,@RequestParam String mode) {
		ModelAndView mav = new ModelAndView("/board/content_noti");
		NoticeDTO dto = boardMapper.getNotice(n_num);
		if(mode==null) {
			mode = "";
		}
		mav.addObject("mode",mode);
		mav.addObject("getNotice",dto);
		return mav;
	}
	
	//자유, 익명 게시글 상세보기
	@RequestMapping("/content_board.do")
	public ModelAndView getBoard(HttpServletRequest req, @RequestParam int board_num, @RequestParam int pageNum, @RequestParam(required = false) Map<String,Integer> params) throws IOException {

		HttpSession session = req.getSession();
		ModelAndView mav = new ModelAndView("/board/content");
		//로그인 체크 
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		String upPath = session.getServletContext().getRealPath("/resources/img");
		BoardDTO dto = boardMapper.getBoard(board_num,"content");
		mav.addObject("getBoard", dto);
		
		String mode = req.getParameter("mode");
		mav.addObject("mode",mode);
		//파일리스트 뽑기
		 List<BoardFilesDTO> flist = boardMapper.getFiles(board_num);
		 mav.addObject("listFile", flist);
		
		// 여기부터 승미가 만짐 (매개변수 int board_num -> Map<String, Integer> params 로 바꿈
		// 댓글 목록 페이지 수 
		 int pageSize = 10;
		if (pageNum == 0) {
			pageNum = 1;
		}
		int currentPage = pageNum;
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountReply(board_num);
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("start", startRow);
		map2.put("end", endRow);
		map2.put("board_num", board_num);
		
		params.put("board_num",board_num);
		params.put("board_replycount",count);
		int board_replycount= boardMapper.setReply(params);

		if (endRow > count)
			endRow = count;
		List<Board_replyDTO> list = null;
		if (count > 0) {
			list = boardMapper.listReply(map2);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
			mav.addObject("pageNum",currentPage);
			mav.addObject("board_replycount",board_replycount);
		}
		mav.addObject("count", count);
		mav.addObject("listReply", list);
		return mav;
	
	}
	//게싯글, 댓글 신고 창 띄우기
	//저는 이 매핑주소에서 자유,익명,중고 게시판의 게시글 댓글의 신고창을 다 띄웠습니다.
	@RequestMapping("/report_board.do")
		public ModelAndView reportBoard(HttpServletRequest req,String mode) {
			ModelAndView mav = new ModelAndView("message");
				if(mode.equals("board")) {
						mav.addObject("board_num",Integer.parseInt(req.getParameter("board_num")));
				}else if(mode.equals("reply")){
						mav.addObject("br_num",Integer.parseInt(req.getParameter("br_num")));
				}else if(mode.equals("sh_board")) {
						mav.addObject("board_num",Integer.parseInt(req.getParameter("board_num")));
				}else {
						mav.addObject("br_num",Integer.parseInt(req.getParameter("br_num")));
				}
					mav.addObject("mode",mode);
					mav.setViewName("board/reportForm");
						return mav;
	}
	//익명, 자유 게시글 , 댓글 신고 넘기기
	@RequestMapping(value="/report_board.do", method = RequestMethod.POST)
	public ModelAndView reportBoardPro(HttpServletRequest req,String mode, @ModelAttribute ReportDTO dto, BindingResult result) {
		ModelAndView mav = new ModelAndView("closeWindow");
		HttpSession session = req.getSession();

		MemberDTO dto3 = (MemberDTO)session.getAttribute("login_mem");
		dto.setMem_num(dto3.getMem_num());
		dto.setReport_content(Integer.parseInt(req.getParameter("board_report")));
		
		if(mode.equals("board")) {
			int board_num = Integer.parseInt(req.getParameter("board_num"));
			dto.setReport_target(board_num);
			dto.setReport_mode("자유,익명게시글");
			boardMapper.report(dto);
			int res = boardMapper.reportBoard(board_num);
				if (res > 0) {
					mav.addObject("msg", "신고되었습니다.");
				} else {
					mav.addObject("msg", "신고 실패, 관리자에게 문의해 주세요");
				}
				
		}else if(mode.equals("sh_board")) {
			int board_num = Integer.parseInt(req.getParameter("board_num"));
			dto.setReport_mode("중고게시글");
			dto.setReport_target(board_num);
			boardMapper.report(dto);
			int res = boardMapper.reportBoard_sh(board_num);
			if (res > 0) {
				mav.addObject("msg", "신고되었습니다.");
			} else {
				mav.addObject("msg", "신고 실패, 관리자에게 문의해 주세요");
			}
			
		}else if(mode.equals("sh_reply")) {
			int br_num = Integer.parseInt(req.getParameter("br_num"));
			dto.setReport_mode("중고댓글");
			dto.setReport_target(br_num);
			boardMapper.report(dto);
			int res = boardMapper.reportReply_sh(br_num);
			if (res > 0) {
				mav.addObject("msg", "신고되었습니다.");
			} else {
				mav.addObject("msg", "신고 실패, 관리자에게 문의해 주세요");
			}
			
		}else if(mode.equals("reply")) {
			int br_num = Integer.parseInt(req.getParameter("br_num"));
			dto.setReport_mode("자유,익명댓글");
			dto.setReport_target(br_num);
			boardMapper.report(dto);
			int res = boardMapper.reportReply(br_num);
				if (res > 0) {
					mav.addObject("msg", "신고되었습니다.");
				} else {
					mav.addObject("msg", "신고 실패, 관리자에게 문의해 주세요");
				}
		}
		return mav;
	}

	//댓글 삭제
		@RequestMapping("/delete_reply.do")
		public ModelAndView deleteReply(HttpServletRequest req, int board_num,int br_num, int pageNum) {
			ModelAndView mav = new ModelAndView("message");
			//로그인 체크 
			HttpSession session= req.getSession();
				MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
				if(mdto == null) {
					session.invalidate();
					mav.setViewName("message_back");
					mav.addObject("msg","로그인 해주세요" );
					return mav;
				}
			int res = boardMapper.deleteReply(br_num);
			if (res > 0) {
				mav.addObject("msg", "삭제 성공! ");
				mav.addObject("url",  "content_board.do?board_num="+board_num+"&pageNum="+pageNum);
			} else {
				mav.addObject("msg", "삭제 실패!");
				mav.addObject("url",  "content_board.do?board_num="+board_num+"&pageNum="+pageNum);
			}

			return mav;
		}
	
	//댓글 입력
	@RequestMapping(value = "/write_reply.do")
	public ModelAndView writeReply(HttpServletRequest req, @ModelAttribute Board_replyDTO dto, BindingResult result) {
		
		int re_group = 0, re_step = 0, re_level = 0;
		
		ModelAndView mav = new ModelAndView("message");
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
			//해당 게시글 번호랑 댓글 번호를 받아옵니다.
			int board_num = dto.getBoard_num();
			int br_num = dto.getBr_num();
			dto.setBoard_num(board_num);
			MemberDTO dto3 = (MemberDTO)session.getAttribute("login_mem");
			dto.setMem_num(dto3.getMem_num());
			dto.setMem_nickname(dto3.getMem_nickname());
			
			//대댓글일때
			if(br_num>0) {
				re_group =dto.getBr_re_group();
				re_step=dto.getBr_re_step();
				re_level=dto.getBr_re_level();
				
			}
			mav.addObject("board_num", board_num);
			mav.addObject("br_num", br_num);
			mav.addObject("br_re_group", re_group);
			mav.addObject("br_re_step", re_step);
			mav.addObject("br_re_level", re_level);
			mav.addObject("dto", dto);
			
			int res = boardMapper.insertReply(dto);
			if (res > 0) {
				mav.addObject("msg", "댓글이 등록 되었습니다.");
				mav.addObject("url", "content_board.do?pageNum=1&board_num="+board_num);
				
			    
				//댓글 알람@@#@#@#@#@#
				MemberDTO dto5 = (MemberDTO)session.getAttribute("login_mem");
				int memNum = alarmMapper.BoardNum(board_num);
				if(dto5.getMem_num()!=memNum) {
				
				String title = alarmMapper.BoardTitle(board_num);
				
				AlarmDTO rm = new AlarmDTO();
				rm.setMem_num(memNum);
				rm.setAlm_cate("댓글");
				rm.setAlm_content(""+title+""+"게시글에 새로운 댓글이 추가되었습니다!");
				rm.setAlm_title("댓글 새소식");

				alarmMapper.addBoardAlarm(rm);
				}
				
			} else {
				mav.addObject("msg", "댓글 등록 실패");
				mav.addObject("url", "content_board.do?board_num"+board_num);
			}
			return mav;
		}
//자유,익명 대댓글
	//이 부분은 ajax로 해당 댓글 밑에 바로 작성 form을 띄우는 작업입니다.
	@ResponseBody
	@RequestMapping("/re_reply.do")
	public ModelAndView re_reply(HttpServletRequest req,int br_num,int pageNum,int board_num) {
		ModelAndView mav = new ModelAndView("/board/Re_replyForm");
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		Board_replyDTO dto = boardMapper.getReply(br_num);
		BoardDTO bdto = boardMapper.getBoard(board_num, "getAnony");
		mav.addObject("dto",dto);
		mav.addObject("bdto",bdto);
		mav.addObject("br_num", br_num);
		mav.addObject("br_re_group",dto.getBr_re_group());
		mav.addObject("br_re_step",dto.getBr_re_step());
		mav.addObject("br_re_level",dto.getBr_re_level());
		mav.addObject("pageNum", pageNum);
		
		return mav;
	}
	//자유, 익명 댓글 수정
	@RequestMapping(value="/update_reply.do", method = RequestMethod.GET)
	public ModelAndView updateReply(HttpServletRequest req,int br_num,int pageNum) {
		ModelAndView mav = new ModelAndView("/board/updateReplyForm");
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		Board_replyDTO dto = boardMapper.getReply(br_num); // 수정할 댓글 정보 가져오기
		mav.addObject("dto",dto);
		mav.addObject("br_num",br_num);
		mav.addObject("pageNum", pageNum);
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="/update_replyOk.do")
	public ModelAndView updateReplyPro(HttpServletRequest req, @RequestParam Map<Object, Object> params) {
		ModelAndView mav = new ModelAndView();
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		String br_num = (String)params.get("br_num"); //댓글 번호를 가져온 후 
		Board_replyDTO dto = boardMapper.getReply(Integer.parseInt(br_num));
		//String pageNum = (String)params.get
		dto.setBr_content((String)params.get("br_content")); // 수정한 내용 set 해줍니다.
		boardMapper.updateReply(dto);
		
		return mav;
	}
	

	//게시글 삭제
	@RequestMapping("/delete_board.do")
	public ModelAndView deleteBoard(HttpServletRequest req,
			@RequestParam(required = false) Map<String, String> params) {
		ModelAndView mav = new ModelAndView("message");
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","로그인 해주세요" );
				return mav;
			}
		int board_num = Integer.parseInt(params.get("board_num"));

		String board_img1 = req.getParameter("board_img1");
		String board_img2 = req.getParameter("board_img2");
		String board_img3 = req.getParameter("board_img3");
		String board_img4 = req.getParameter("board_img4");
	
		
		int res = boardMapper.deleteBoard(board_num);
		if (res > 0) {
			String upPath = (String) session.getAttribute("upPath");

			File file1 = new File(upPath, board_img1); // 이미지랑 파일 싹다 지워줍니다.
			File file2 = new File(upPath, board_img2);
			File file3 = new File(upPath, board_img3);
			File file4 = new File(upPath, board_img4);

			if (file1.exists() || file2.exists() || file3.exists() || file4.exists()) {
				file1.delete();
				file2.delete();
				file3.delete();
				file4.delete();
			}
			//파일리스트 뽑기
			 List<BoardFilesDTO> flist = boardMapper.getFiles(board_num);
			 String filename = req.getParameter("filename");
				String upPath1 = (String) session.getAttribute("upPath1");
				File file5 = new File(upPath1, filename);
				 if (file5.exists()) {
			            file5.delete();
			            for (int i = 0; i < flist.size(); i++) {
			               int res2 = boardMapper.fileDelete(flist.get(i).getBoard_num());
			               File file6 = new File(upPath1 + "\\" + flist.get(i).getSavename());
			               if (file6.exists()) {
			                  file6.delete();
			               }
			            }
			}
				 mav.addObject("msg", "글 삭제 성공");
		} else {
			mav.addObject("msg", "삭제 실패");
			mav.addObject("url", "board_free.do?mode="+params.get("mode"));
		}
		mav.addObject("url", "board_free.do?mode="+params.get("mode"));
		return mav;
	}
	
	
	//게시글 수정버튼 클릭시
	@RequestMapping(value = "/update_board.do", method = RequestMethod.GET)
	public ModelAndView updateBoard(HttpServletRequest req, int board_num, String mode) {
		BoardDTO dto = boardMapper.getBoard(board_num, "update");
		List<BoardFilesDTO> list = boardMapper.getFiles(board_num);
		ModelAndView mav = new ModelAndView();
		mav.addObject("getBoard", dto);
		mav.addObject("getFiles", list);
		mav.addObject("mode", mode);
		mav.setViewName("board/updateForm");
		return mav;
	}
	//게시글 수정 처리
	@RequestMapping(value = "/update_board.do", method = RequestMethod.POST)
	public ModelAndView updateOkBoard(HttpServletRequest req,@RequestParam("filename") List<MultipartFile> multiFileList, @ModelAttribute BoardDTO dto, BindingResult result)
			throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView("message");
		//로그인 체크 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message");
				mav.addObject("msg","로그인 해주세요" );
				mav.addObject("url","login.do" );
				return mav;
			}
		int board_num = Integer.parseInt(req.getParameter("board_num"));
		if (result.hasErrors()) {
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		MultipartFile mf1 = mr.getFile("board_img1");
		MultipartFile mf2 = mr.getFile("board_img2");
		MultipartFile mf3 = mr.getFile("board_img3");
		MultipartFile mf4 = mr.getFile("board_img4");
		MultipartFile mf5 = mr.getFile("filename");
		
		String board_img1 = mf1.getOriginalFilename();
		String board_img2 = mf2.getOriginalFilename();
		String board_img3 = mf3.getOriginalFilename();
		String board_img4 = mf4.getOriginalFilename();
		
		String upPath = session.getServletContext().getRealPath("/resources/img");
		String upPath1 = session.getServletContext().getRealPath("/resources/files");
		
		UUID uuid = UUID.randomUUID();

		if (!mf1.isEmpty()) { // 이미 이미지가 있고, 수정할 시
			board_img1 = uuid.toString() + "_" + board_img1;
			File file1 = new File(upPath, board_img1);
			mf1.transferTo(file1);
			
			File file11 = new File(upPath, req.getParameter("board_img1-2"));
			if(file11.exists()) { // 이미 파일이 존재한다면 삭제
				file11.delete();
			}
			dto.setBoard_img1(board_img1); // 새로운 이미지 dto에 넣어주기
			
		} else if (mf1.isEmpty()) { // 이미지 첨부가 안되어 있고 수정할때 첨부
			dto.setBoard_img1(req.getParameter("board_img1-2"));
		}

		if (!mf2.isEmpty()) { 
			board_img2 = uuid.toString() + "_" + board_img2;
			
			File file2 = new File(upPath, board_img2);
			mf2.transferTo(file2);
			
			File file22 = new File(upPath, req.getParameter("board_img2-2"));
			if(file22.exists()) {
				file22.delete();
			}
			dto.setBoard_img2(board_img2);
			
		} else if (mf2.isEmpty()) {
			dto.setBoard_img2(req.getParameter("board_img2-2"));
		}

		if (!mf3.isEmpty()) { 
			board_img3 = uuid.toString() + "_" + board_img3;
			
			File file3 = new File(upPath, board_img3);
			mf3.transferTo(file3);
			
			File file33 = new File(upPath, req.getParameter("board_img3-2"));
			if(file33.exists()) {
				file33.delete();
			}
			dto.setBoard_img3(board_img3);
			
		} else if (mf3.isEmpty()) {
			dto.setBoard_img3(req.getParameter("board_img3-2"));
		}
		
		
		if (!mf4.isEmpty()) { 
			board_img4 = uuid.toString() + "_" + board_img4;
			
			File file4 = new File(upPath, board_img4);
			mf4.transferTo(file4);
			
			File file44 = new File(upPath, req.getParameter("board_img4-2"));
			if(file44.exists()) {
				file44.delete();
			}
			dto.setBoard_img4(board_img4);
			
		} else if (mf4.isEmpty()) {
			dto.setBoard_img4(req.getParameter("board_img4-2"));
		}

		BoardFilesDTO dto2 = new BoardFilesDTO();
		String mode = req.getParameter("mode");
		int res = boardMapper.updateBoard(dto);
		if (res > 0) {
			// 파일 업다운로드 수정
			 File fileCheck = new File(upPath1);
	         if (!fileCheck.exists())
	            fileCheck.mkdirs();
	         List<Map<String, String>> fileList = new ArrayList<>();
	         if(multiFileList == null || multiFileList.get(0).getOriginalFilename().equals("")) {
	        	 mav.addObject("msg", "수정성공! ");
	        		if(mode.equals("anony")) {
	 			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=anony");
	        		}else {
	        		mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=");
	        		}
	 			mav.addObject("getBoard", dto);
	 			return mav;
	         }else if (multiFileList.get(0).getOriginalFilename() != "") {
	            List<BoardFilesDTO> list = boardMapper.getFiles(board_num);
	            for(BoardFilesDTO fdto : list) {
	               int res2 = boardMapper.fileDelete(fdto.getBoard_num());
	               File file2 = new File(upPath1 + "\\" + fdto.getSavename());
	               if(file2.exists()) file2.delete();
	               else {
	            		mav.addObject("msg", "수정실패!");
	            		 mav.addObject("msg", "수정성공! ");
	 	        		if(mode.equals("anony")) {
	 	        			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=anony");
	 	        		}else {
	 	        			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=");
	 	        		}
	               }
	            }
	         }
	         //수정 할 때 새로운 파일 추가시.
	         for (int i = 0; i < multiFileList.size(); i++) {
	            String originFile = multiFileList.get(i).getOriginalFilename();
	            int filesize = (int) multiFileList.get(i).getSize();
	            String ext = originFile.substring(originFile.lastIndexOf("."));
	            String changeFile = UUID.randomUUID().toString() + ext;
	            dto2.setFilename(originFile);
	            dto2.setSavename(changeFile);
	            dto2.setFilesize(filesize);
	            int num = board_num;
	            dto2.setBoard_num(num);
	            int res2 = boardMapper.fileInsert(dto2);
	            Map<String, String> map = new HashMap<>();
	            map.put("filename", originFile);
	            map.put("savename", changeFile);

	            fileList.add(map);
	         }
	         // 파일 업로드
	         try {
	            for (int i = 0; i < multiFileList.size(); i++) {
	               File uploadFile = new File(upPath1 + "\\" + fileList.get(i).get("savename"));
	               multiFileList.get(i).transferTo(uploadFile);
	            }
	            System.out.println("다중 파일 업로드 성공");
	         } catch (IllegalStateException | IOException e) {
	            System.out.println("다중파일 업로드 실패 ㅠㅠ");
	            for (int i = 0; i < multiFileList.size(); i++) {
	               new File(upPath1 + "\\" + fileList.get(i).get("savename")).delete();
	            }
	            e.printStackTrace();
	         }
			mav.addObject("msg", "수정성공! ");
			 mav.addObject("msg", "수정성공! ");
     		if(mode.equals("anony")) {
			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=anony");
     		}else {
     		mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=");
     		}
			mav.addObject("getBoard", dto);
		} else {
			mav.addObject("msg", "수정실패!");
			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode="+req.getParameter("mode"));
		}
		return mav;
	}
	
	//자유게시판 검색
	@RequestMapping("board_free_find.do")
	public ModelAndView freeFind(HttpServletRequest req, @RequestParam java.util.Map<String,Object> params) {
		ModelAndView mav = new ModelAndView("board/list_free");
			
		// select 문에서 받아온 값을 지정해줍니다.
		String select = (String) params.get("select");
			if(select.equals("writer")) {
				select = "m.mem_nickname";
			}else if(select.equals("title")){
				select = "board_title";
			}else {
				select = "board_content";
			}
			//검색창에 입력한 string을 받아옵니다.
			String searchString =(String) params.get("searchString");
			
			params.put("search", select);
			params.put("searchString", searchString); 
			
			//조회수 순 
			List<BoardDTO> readlist = boardMapper.readlist();
			mav.addObject("readlist", readlist);
			//댓글순
			List<BoardDTO> replylist = boardMapper.replylist();
			mav.addObject("replylist", replylist);
			
			//검색시 페이지 넘버
			int pageSize = 10;
			String pageNum = (String) params.get("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int count = boardMapper.getCountFind(params);
			
			params.put("start", startRow);
			params.put("end", endRow);

			if (endRow > count)
				endRow = count;
			List<BoardDTO> list = null;
			if (count > 0) {
				list =  boardMapper.findFree(params);
				int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
				int pageBlock = 2;
				int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
				int endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;

				mav.addObject("startPage", startPage);
				mav.addObject("endPage", endPage);
				mav.addObject("pageBlock", pageBlock);
				mav.addObject("pageCount", pageCount);
			}
			
				mav.addObject("count", count);
				mav.addObject("listBoard", list);
		
				return mav;
	}
	
	//익명게시판 검색
	@RequestMapping("board_anony_find.do")
	public ModelAndView anonyFind(HttpServletRequest req,@RequestParam String searchString, @RequestParam java.util.Map<String,Object> params) {
		ModelAndView mav = new ModelAndView("board/list_anony");
		String select = (String) params.get("select");
		searchString =(String) params.get("searchString");
		 
		if(select.equals("title")){
			select = "board_title";
		}else {
			select = "board_content";
		}
		params.put("search", select);
		params.put("searchString", searchString);
		
		//조회수 순 
				List<BoardDTO> readlist = boardMapper.readlist_a();
				mav.addObject("readlist", readlist);
				//댓글순
				List<BoardDTO> replylist = boardMapper.replylist_a();
				mav.addObject("replylist", replylist);
				
		//검색시 페이지 넘버
		int pageSize = 10;
		String pageNum = (String) params.get("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountFind_anony(params);
		
		params.put("start", startRow);
		params.put("end", endRow);

		if (endRow > count)
			endRow = count;
		List<BoardDTO> list = null;
		if (count > 0) {
			list = boardMapper.findAnony(params);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
			mav.addObject("count", count);
			mav.addObject("listBoard", list);
		
		return mav;
	}
}