package cmsteam2.common.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MetaData")
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_MetaData;

    @ElementCollection
    @CollectionTable(name = "Topics", joinColumns = @JoinColumn(name = "id_Topics"))
    @Column(name = "topic")
    private Set<String> topics = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "Keywords", joinColumns = @JoinColumn(name = "id_Keyword"))
    @Column(name = "keyword")
    private Set<String> keywords = new HashSet<>();

    private String name;

    @Column(name = "frn_ResearchPaper_Id")
    private int id_researchPaper;
}