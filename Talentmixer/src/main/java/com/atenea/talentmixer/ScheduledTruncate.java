package com.atenea.talentmixer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class ScheduledTruncate {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?") 
	public void truncate() {
		entityManager.createNativeQuery("TRUNCATE TABLE project").executeUpdate();
		entityManager.createNativeQuery("DELETE FROM user_account WHERE id > 2").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO project (title,summary,description,created_at,image,id_user_account) VALUES ('Bot de Telegram con Python', 'Queremos poder descargar imágenes desde Telegram móvil de forma sencilla.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sit amet egestas tortor. Vivamus efficitur ligula sapien, nec scelerisque augue condimentum vel. Vestibulum iaculis laoreet turpis, in aliquam lacus condimentum eget. Proin iaculis gravida arcu quis euismod. Suspendisse accumsan odio dui, id commodo neque blandit a. Sed odio justo, pulvinar eget maximus ac, laoreet et tortor. Sed at nulla dictum, dignissim turpis eu, dapibus mi. Quisque vitae tellus rhoncus, rutrum enim vel, congue nibh. Suspendisse diam elit, varius sed commodo quis, mollis id dolor. Morbi lectus neque, mollis eget condimentum eu, venenatis ut eros. Donec euismod elit in mi varius varius. Nullam ac pharetra nisi, quis pharetra justo. Maecenas vitae quam ut leo blandit imperdiet. Quisque tempus, neque ac commodo dictum, orci augue elementum ex, non ornare nibh lacus sed ante. Quisque molestie condimentum augue non placerat. Suspendisse sagittis, metus non tempor pretium, nibh erat finibus sem, sed auctor dui nisi sed justo.', '2023-03-30', 'images/projects/1681743373845.png', 1)").executeUpdate();	
		entityManager.createNativeQuery("INSERT INTO project (title,summary,description,created_at,image,id_user_account) VALUES ('Aplicación Angular con Spring', 'Aplicación para gestionar eventos públicos.', 'Nam non sodales nisi. Ut malesuada lectus ac justo dictum, et dapibus enim viverra. Nam rutrum lorem dui, vitae auctor orci consectetur in. Ut sed enim in nibh fermentum pharetra. Vestibulum eu euismod libero. Cras et dui id quam laoreet accumsan. Duis ac pretium sapien, non ullamcorper libero. Nam rutrum mauris quis eros congue accumsan.', '2023-03-25', 'images/projects/1681743608002.png', 1)").executeUpdate();	
		entityManager.createNativeQuery("INSERT INTO project (title,summary,description,created_at,image,id_user_account) VALUES ('Ktor + React', 'Online candle shop!', 'Nunc non mauris nisl. Mauris mattis lacus non ante auctor condimentum vitae non diam. Nullam vulputate ex maximus augue elementum sollicitudin. Aliquam dignissim velit augue, et condimentum enim ornare a. Etiam orci mauris, cursus eget lectus et, molestie pharetra risus. Fusce faucibus lorem magna, eget commodo magna malesuada eget. Sed tempus lacus a nisi iaculis, non mattis augue facilisis. Fusce varius eu nisi eu lobortis. In in felis nisl. Phasellus tristique ipsum pharetra libero auctor condimentum. Integer ullamcorper quis risus eget molestie. Praesent at aliquam turpis. Sed non magna ac arcu imperdiet ultrices vel at ipsum. Sed fringilla risus eget felis dictum, at posuere urna mattis.\r\n"
				+ "\r\n"
				+ "Vestibulum maximus rutrum neque a bibendum. Cras eget consequat ex. Sed metus dolor, congue et sodales eu, feugiat eget turpis. Curabitur hendrerit pellentesque erat, sit amet viverra velit dapibus ac. Phasellus non nunc dui. Mauris vestibulum tellus purus, eu efficitur arcu congue ac. Etiam vel tristique ex. Nunc sem justo, convallis vitae lacinia at, scelerisque vel nisi. Proin tincidunt est diam, sed facilisis magna accumsan sed. In sit amet eros mattis, semper eros at, aliquet leo.', '2022-07-30', 'images/projects/1681743716730.png', 2)").executeUpdate();	
	}


}
