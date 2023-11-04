const doctorProfileData = {
  doctorId: "DMH001",
  firstName: "Mehrab",
  lastName: "Hasan",
  email: "mehrab.hasan@example.com",
  phone: "123-456-7890",
  gender: "Male",
  dateOfBirth: "1980-05-15",
  profilePhoto:
    "https://hips.hearstapps.com/hmg-prod/images/portrait-of-a-happy-young-doctor-in-his-clinic-royalty-free-image-1661432441.jpg?crop=0.66698xw:1xh;center,top&resize=1200:*",
  nidNo: "1234567890",
  residence: "123 Main Street, City, Country",
  bio: "I am a dedicated and experienced doctor with a strong commitment to providing quality healthcare. I specialize in cardiology and have been serving patients for over 15 years.",
  experience: "15 years",
  license: "MD123456",
  specializations: "Cardiology, Internal Medicine",
  qualifications: [
    {
      degree: "Doctor of Medicine",
      institute: "Medical College",
      year: "2005",
    },
    {
      degree: "Bachelor of Science in Biology",
      institute: "University of Science",
      year: "2001",
    },
  ],
  certifications: [
    {
      degree: "Cardiology Certification",
      institute: "Cardiology Institute",
      year: "2010",
    },
    {
      degree: "Internal Medicine Certification",
      institute: "Internal Medicine Institute",
      year: "2006",
    },
  ],
  treatmentsCount: 5000,
  feedbacksCount: 55,
  feedbacks: [
    {
      patientId: "P003",
      comment:
        "Dr. Hasan is the best cardiologist I've ever met. Highly recommended.",
      rating: 1,
      date: "12-11-2023",
    },
    {
      patientId: "P004",
      comment: "Excellent service! Dr. Hasan is knowledgeable and caring.",
      rating: 5,
      date: "04-11-2023",
    },
    {
      patientId: "P005",
      comment:
        "I'm very satisfied with the treatment I received from Dr. Mehrab Hasan.",
      rating: 3,
      date: "03-10-2023",
    },
    {
      patientId: "P006",
      comment:
        "Dr. Hasan's expertise in cardiology is unparalleled. I'm grateful for his care. Dr. Hasan's expertise in cardiology is unparalleled. I'm grateful for his care. Dr. Hasan's expertise in cardiology is unparalleled. I'm grateful for his care.",
      rating: 2,
      date: "12-07-2023",
    },
    {
      patientId: "P007",
      comment:
        "I had a wonderful experience with Dr. Hasan. He's a great doctor.",
      rating: 4,
      date: "10-07-2023",
    },
  ],
  availability: {
    onsite: [
      {
        date: "2023-11-14",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "04:00 PM", toTime: "05:00 PM" },
        ],
      },
      {
        date: "2023-11-15",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
      {
        date: "2023-11-16",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
      {
        date: "2023-11-17",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "03:00 PM", toTime: "04:00 PM" },
        ],
      },
      {
        date: "2023-11-18",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "01:00 PM", toTime: "02:00 PM" },
        ],
      },
      {
        date: "2023-11-19",
        timeslots: [
          { fromTime: "11:00 AM", toTime: "12:00 PM" },
          { fromTime: "03:00 PM", toTime: "04:00 PM" },
        ],
      },
      {
        date: "2023-11-20",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "04:00 PM", toTime: "05:00 PM" },
        ],
      },
      {
        date: "2023-11-21",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
    ],
    telemedicine: [
      {
        date: "2023-11-14",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "04:00 PM", toTime: "05:00 PM" },
        ],
      },
      {
        date: "2023-11-15",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
      {
        date: "2023-11-16",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
      {
        date: "2023-11-17",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "03:00 PM", toTime: "04:00 PM" },
        ],
      },
    ],
  },
};

export default doctorProfileData;
