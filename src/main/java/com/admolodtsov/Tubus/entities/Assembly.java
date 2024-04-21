package com.admolodtsov.Tubus.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "assemblies")
public class Assembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "'поле обязательное для заполнения'")
    @Size(min= 2, message ="поле должно содержать минимум 2 символа")
    @Column(name="title")
    private String title;

    @NotBlank(message = "'поле должно содержать 15 символов'")
    @Size(min= 15, message ="'поле должно содержать 15 символов'")
    @Size(max= 15, message ="'поле должно содержать 15 символов'")
    @Column(name="designation")
    private String designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designProject_id")
    private DesignProject designProject;

    @Column(name="creation_date")
    private String date;

    @NotBlank(message = "'поле не должно быть пустым'")
    @Column(name="doc_description")
    private String docDescription;

    @NotBlank(message = "'поле не должно быть пустым'")
    @Column(name="doc_type")
    private String docType;

    @Column(name="version")
    private String version;

    //Внести сведения об изменениях

    @NotBlank(message = "'поле не должно быть пустым'")
    @Column(name="doc_format")
    private String docFormat;

    @NotBlank(message = "'поле не должно быть пустым'")
    @Column(name="sheet_number")
    private String sheetNumber;

    @NotBlank(message = "'поле не должно быть пустым'")
    @Column(name="litera")
    private String litera;

    @Column(name="status")
    private String status;

    public Assembly() {
    }

    public Assembly(String title, String designation, String date,
                    String docDescription, String docType, String version,
                    String docFormat, String sheetNumber, String litera, String status) {
        this.title = title;
        this.designation = designation;
        this.date = date;
        this.docDescription = docDescription;
        this.docType = docType;
        this.version = version;
        this.docFormat = docFormat;
        this.sheetNumber = sheetNumber;
        this.litera = litera;
        this.status = status;
    }

}
