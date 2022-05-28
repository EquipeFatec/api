package sanjavaley.heyalexia.Email;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sanjavaley.heyalexia.Email.envio.Mailer;
import sanjavaley.heyalexia.Email.envio.Mensagem;

import java.util.Arrays;

public class SpringEmailMain {

    public static void main(String email, String assunto, String corpo) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                SpringEmailMain.class.getPackage().getName());

        Mailer mailer = applicationContext.getBean(Mailer.class);
        mailer.enviar(new Mensagem("Hey Alexia <fatec2022api@gmail.com>",
                Arrays.asList(String.format("Confirmação de Cadastro <%s>", email)), assunto, corpo));

        applicationContext.close();
    }


}
