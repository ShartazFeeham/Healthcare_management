import Index from "components/examples/Index";
import Profile from "components/examples/Profile";
import Maps from "components/examples/Maps";
import Tables from "components/examples/Tables";
import Icons from "components/examples/Icons";
import Login from "components/accounts/Login";
import ForgottenPassword from "components/accounts/ForgottenPassword";
import VerifyAccount from "components/accounts/VerifyAccount";
import RegisterAdmin from "components/accounts/RegisterAdmin";
import RegisterPatient from "components/accounts/RegisterPatient";
import RegisterDoctor from "components/accounts/RegisterDoctor";
import PatientProfile from "components/profiles/PatientProfile";
import DoctorProfile from "components/profiles/DoctorProfile";
import EditDoctorProfile from "components/profiles/EditDoctorProfile";
import EditPatientProfile from "components/profiles/EditPatientProfile";
import CreateMedicine from "components/pharma/CreateMedicine";
import UpdateMedicine from "components/pharma/UpdateMedicine";
import MedicineList from "components/pharma/MedicineList";
import MedicineDetails from "components/pharma/MedicineDetails";
import Community from "components/community/Community";
import CreateDisease from "components/disease/CreateDisease";
import DoctorsList from "components/accounts/DoctorList";
import PatientsList from "components/accounts/PatientsList";
import Logout from "components/accounts/Logout";

var routes = [
  {
    path: "/index",
    name: "Home",
    icon: "fa fa-home text-info",
    component: <Index />,
    layout: "/health",
    role: "ADMIN PATIENT DOCTOR PUBLIC",
  },
  {
    path: "/doctor",
    name: "Dashboard",
    icon: "fa fa-list-alt text-warning",
    component: <Index />,
    layout: "/health",
    role: "DOCTOR",
  },
  {
    path: "/patient",
    name: "Dashboard",
    icon: "fa fa-list-alt text-warning",
    component: <Index />,
    layout: "/health",
    role: "PATIENT",
  },
  {
    path: "/admin",
    name: "Dashboard",
    icon: "fa fa-list-alt text-warning",
    component: <Index />,
    layout: "/health",
    role: "ADMIN",
  },
  {
    path: "/login",
    name: "Login",
    icon: "ni ni-key-25 text-info",
    component: <Login />,
    layout: "/public",
    role: "PUBLIC",
  },
  {
    path: "/register-patient",
    name: "Register",
    icon: "ni ni-circle-08 text-pink",
    component: <RegisterPatient />,
    layout: "/public",
    role: "PUBLIC",
  },
  {
    path: "/forgotten-password",
    name: "Forgotten password",
    icon: "ni ni-circle-08 text-pink",
    component: <ForgottenPassword />,
    layout: "/public",
    role: "PUBLIC",
  },
  {
    path: "/verify-email",
    name: "Verify account",
    icon: "ni ni-circle-08 text-pink",
    component: <VerifyAccount />,
    layout: "/public",
    role: "PUBLIC",
  },
  {
    path: "/register-admin",
    name: "Create admin",
    icon: "ni ni-circle-08 text-pink",
    component: <RegisterAdmin />,
    layout: "/health",
    role: "ADMIN",
  },
  {
    path: "/register-doctor",
    name: "Join as doctor",
    icon: "ni ni-circle-08 text-pink",
    component: <RegisterDoctor />,
    layout: "/public",
    role: "PUBLIC",
  },
  {
    path: "/patients/:patientId",
    name: "Profile",
    icon: "ni ni-single-02 text-pink",
    component: <PatientProfile />,
    layout: "/health",
    role: "PATIENT",
  },
  {
    path: "/doctors/:doctorId",
    name: "Profile",
    icon: "ni ni-single-02 text-gray",
    component: <DoctorProfile />,
    layout: "/health",
    role: "DOCTOR",
  },
  {
    path: "/doctors/edit-profile",
    name: "Edic Profile",
    icon: "fas fa-user-edit text-gray",
    component: <EditDoctorProfile />,
    layout: "/health",
    role: "DOCTOR",
  },
  {
    path: "/patients/edit-profile",
    name: "Edic Profile",
    icon: "fas fa-user-edit text-pink",
    component: <EditPatientProfile />,
    layout: "/health",
    role: "PATIENT",
  },
  {
    path: "/medicines/create",
    name: "Create new medicine",
    icon: "fa-solid fa-capsules text-pink",
    component: <CreateMedicine />,
    layout: "/health",
    role: "ADMIN DOCTOR",
  },
  {
    path: "/medicines/update/:medId",
    name: "Update a medicine",
    icon: "fa-solid fa-pills text-pink",
    component: <UpdateMedicine />,
    layout: "/health",
    role: "ADMIN DOCTOR",
  },
  {
    path: "/medicines",
    name: "Medicines list",
    icon: "fa-solid fa-tablets text-info",
    component: <MedicineList />,
    layout: "/common",
    role: "ADMIN PATIENT DOCTOR PUBLIC",
  },
  {
    path: "/medicines/:medId",
    name: "Medicine details",
    icon: "fa-solid fa-file-prescription text-info",
    component: <MedicineDetails />,
    layout: "/common",
    role: "ADMIN PATIENT DOCTOR PUBLIC",
  },
  {
    path: "/community",
    name: "Community",
    icon: "fa-solid fa-users text-success",
    component: <Community />,
    layout: "/health",
    role: "ADMIN PATIENT DOCTOR",
  },
  {
    path: "/diseases/create",
    name: "Diseases",
    icon: "fa-solid fa-disease text-warning",
    component: <CreateDisease />,
    layout: "/health",
    role: "ADMIN DOCTOR",
  },
  {
    path: "/doctors",
    name: "Doctors List",
    icon: "fa-solid fa-users-line text-skyblue",
    component: <DoctorsList />,
    layout: "/health",
    role: "ADMIN",
  },
  {
    path: "/patients",
    name: "Patients List",
    icon: "fa-solid fa-users-rays text-gray",
    component: <PatientsList />,
    layout: "/health",
    role: "ADMIN",
  },
  {
    path: "/logout",
    name: "Sign out",
    icon: "fa fa-sign-out text-black",
    component: <Logout />,
    layout: "/health",
    role: "ADMIN PATIENT DOCTOR",
  },
];
export default routes;
