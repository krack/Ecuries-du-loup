package service.news;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.news.NouvelleDAO;
import donnees.news.Nouvelle;
import edlcode.EdlCode;
import edlcode.EdlCodeEncodageException;
import fr.ecurie_du_loup.generique_util.service.DataBaseServiceWithDaoIdLongUtilAndLongId;

public class NouvelleManagerImpl extends DataBaseServiceWithDaoIdLongUtilAndLongId<Nouvelle>implements NouvelleManager {
	@Autowired
	private EdlCode edlCode;
	
	public void setEdlCode(EdlCode edlCode) {
		this.edlCode = edlCode;
	}
	@Override
	public List<Nouvelle> recupererDernieresNouvelles(int nombreDerniereNouvelle, String pathServeur) {
		return moulinetteEdlCode(((NouvelleDAO) this.dao).getDernieresNouvelles(nombreDerniereNouvelle), pathServeur);
	}
	private List<Nouvelle> moulinetteEdlCode(List<Nouvelle> listeAvant,String pathServeur) {
		List<Nouvelle> listeModifier = new ArrayList<Nouvelle>();
		for (Nouvelle nouvelle : listeAvant) {
			listeModifier.add(moulinetteEdlCode(nouvelle, pathServeur));	
		}

		return listeModifier;
	}
	
	private Nouvelle moulinetteEdlCode(Nouvelle nouvelle, String pathServeur){
		Nouvelle nouvelleEdlCode = new Nouvelle();
		nouvelleEdlCode.setAuteur(nouvelle.getAuteur());
		nouvelleEdlCode.setDateCreation(nouvelle.getDateCreation());
		nouvelleEdlCode.setDateDerniereModification(nouvelle
				.getDateDerniereModification());
		nouvelleEdlCode.setId(nouvelle.getId());
		nouvelleEdlCode.setTitre(nouvelle.getTitre());

		String contenuMouline;
		try {

			contenuMouline = this.edlCode.parse(nouvelle.getContenu(),	pathServeur);
		} catch (EdlCodeEncodageException e) {
			contenuMouline = nouvelle.getContenu();
		}
		nouvelleEdlCode.setContenu(contenuMouline);
		return nouvelleEdlCode;
	}
	@Override
	public Nouvelle getFormatedNews(long newsId, String contextPath) {
		Nouvelle news = this.getById(newsId);
		return this.moulinetteEdlCode(news, contextPath);
	}
	
}
