package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.Pessoa;

@ManagedBean (name = "uploadXml")
public class UploadArquivoXml {

	private UploadedFile arquivo;
	private Pessoa pessoa;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public void upload() throws Exception, SAXException, IOException {
		try {
			if (this.arquivo != null || !this.arquivo.getFileName().equals(""))
				this.montaDados();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Informação", "Lista de arquivos importada!!"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		} catch (NullPointerException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Informação", "Não foi encontrado nenhum arquivo para importação!!"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	private void montaDados() throws Exception, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(this.arquivo.getInputstream());

		Element element = doc.getDocumentElement();
		NodeList node = element.getChildNodes();

		this.pessoa = null;

		if (node.getLength() > 0) {
			this.pessoa = new Pessoa();
			for (int i = 0; i < node.getLength(); i++) {
				if (node.item(i).getNodeName().equals("codigo")) {
					this.pessoa.setId(Integer.parseInt(node.item(i).getTextContent()));
				}
				if (node.item(i).getNodeName().equals("nome")) {
					this.pessoa.setNome(node.item(i).getTextContent());
				}
				if (node.item(i).getNodeName().equals("dataNascimento")) {
					pessoa.setDataNascimento(node.item(i).getTextContent());
				}

				if (pessoa != null) {
					this.pessoas.add(pessoa);
				}
			}
		}

	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
