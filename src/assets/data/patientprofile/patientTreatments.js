const patientDataTreatments = [
  {
    diseaseName: "Common Cold",
    treatedBy: "Dr. Smith",
    issueDate: "2023-03-15",
    closingDate: "2023-03-25",
    doctorComment: "Rest and drink fluids",
    medicines: [
      { name: "MED32", id: "MED001" },
      { name: "MED54", id: "MED002" },
      { name: "MED78", id: "MED003" },
    ],
    diagnoses: [
      { id: "DIAG001", name: "Upper Respiratory Infection" },
      { id: "DIAG002", name: "Fever" },
      { id: "DIAG003", name: "Sore Throat" },
    ],
    progression: "Recovering",
  },
  {
    diseaseName: "Hypertension",
    treatedBy: "Dr. Johnson",
    issueDate: "2023-04-10",
    closingDate: "2023-05-20",
    doctorComment: "Monitor blood pressure regularly",
    medicines: [
      { name: "MED32", id: "MED004" },
      { name: "MED54", id: "MED005" },
      { name: "MED78", id: "MED006" },
    ],
    diagnoses: [
      { id: "DIAG004", name: "High Blood Pressure" },
      { id: "DIAG005", name: "Hypertension Risk Factors" },
    ],
    progression: "Stable",
  },
  {
    diseaseName: "Allergic Reaction",
    treatedBy: "Dr. Williams",
    issueDate: "2023-02-05",
    closingDate: "2023-02-15",
    doctorComment: "Avoid allergens",
    medicines: [
      { name: "MED32", id: "MED007" },
      { name: "MED54", id: "MED008" },
      { name: "MED78", id: "MED009" },
    ],
    diagnoses: [
      { id: "DIAG006", name: "Allergic Rhinitis" },
      { id: "DIAG007", name: "Skin Allergy" },
    ],
    progression: "Recovered",
  },
];

export default patientDataTreatments;
