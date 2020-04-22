package hh.swd20.harjtyo.musicrepo;

import static org.assertj.core.api.Assertions.assertThat;

import hh.swd20.harjtyo.musicrepo.webcontroller.GenreController;
import hh.swd20.harjtyo.musicrepo.webcontroller.RestServiceController;
import hh.swd20.harjtyo.musicrepo.webcontroller.SessionController;
import hh.swd20.harjtyo.musicrepo.webcontroller.SongController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
class MusicrepoApplicationTests {

	@Autowired
	private GenreController genreController;

	@Autowired
	private RestServiceController restServiceController;

	@Autowired
	private SessionController sessionController;

	@Autowired
	private SongController songController;

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Test
	void genreContextLoads() throws Exception {
		assertThat(genreController).isNotNull();
	}
	@Test
	void sessionContextLoads() throws Exception {
		assertThat(sessionController).isNotNull();
	}
	@Test
	void songContextLoads() throws Exception {
		assertThat(songController).isNotNull();
	}
	@Test
	void restContextLoads() throws Exception {
		assertThat(restServiceController).isNotNull();
	}
	@Test
	void userContextLoads() throws Exception {
		assertThat(userDetailService).isNotNull();
	}

}
