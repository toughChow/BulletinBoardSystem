package bbs.test;

import java.util.List;

import org.junit.Test;

import bbs.dao.PostDao;
import bbs.dao.impl.PostDaoImpl;
import bbs.domain.Post;
import bbs.web.Page;

public class PostDaoImplTest {
	private PostDao postDao = new PostDaoImpl();

	@Test
	public void testGetPost() {
		//CriteriaPost cp = new CriteriaPost(5);
		Page<Post> page = postDao.getPage(13);
		
		System.out.println(page.getPageNo());
		System.out.println(page.getPageSize());
		System.out.println(page.getList());
		System.out.println(page.getTotalPageNumber());
	}

	@Test
	public void testGetPageList() {
		Page page = new Page();
		List<Post> post = postDao.getPageList(1, page.getPageSize());
		for(Post post2:post){
			System.out.println(post2);
		}
//		System.out.println(post);
	}

}
