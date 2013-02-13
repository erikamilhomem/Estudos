package br.com.erika.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.Session;

/**
 * Em todas as fases do JSF esse Listener será executada, sendo que, antes da
 * fase inicia a transação, obeter a sessão do hibernate, abrir a sessão, inicia
 * transação e colocar no mapa da requisição a session do hibernate. Após da
 * fase a trasação será comitada, caso ocorra algum erro vai fazer o rollback da
 * transação e finalmente fecha a sessão indenpendente de ter havido exito ou
 * não no processo de transação.
 * 
 * @author Erika Milhomem
 * 
 */
public class PhaseListenerErika implements PhaseListener {

	// Antes da Fase
	@Override
	public void beforePhase(PhaseEvent fase) {
		if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
			System.out.println("Antes da fase: " + getPhaseId());
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			FacesContextUtil.setRequestSession(session);
		}
	}

	// Depois da fase
	@Override
	public void afterPhase(PhaseEvent fase) {
		System.out.println("Depois da fase: " + getPhaseId());
		if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
			Session session = FacesContextUtil.getRequestSession();
			try {
				session.getTransaction().commit();

			} catch (Exception e) {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();

				}
			} finally {
				session.close();
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
