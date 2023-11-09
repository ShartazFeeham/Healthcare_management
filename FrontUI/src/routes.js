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

var routes = [
  {
    path: "/index",
    name: "Dashboard",
    icon: "ni ni-tv-2 text-primary",
    component: <Index />,
    layout: "/health",
  },
  {
    path: "/icons",
    name: "Icons",
    icon: "ni ni-planet text-blue",
    component: <Icons />,
    layout: "/health",
  },
  {
    path: "/maps",
    name: "Maps",
    icon: "ni ni-pin-3 text-orange",
    component: <Maps />,
    layout: "/health",
  },
  {
    path: "/user-profile",
    name: "User Profile",
    icon: "ni ni-single-02 text-yellow",
    component: <Profile />,
    layout: "/health",
  },
  {
    path: "/tables",
    name: "Tables",
    icon: "ni ni-bullet-list-67 text-red",
    component: <Tables />,
    layout: "/health",
  },
  {
    path: "/login",
    name: "Login",
    icon: "ni ni-key-25 text-info",
    component: <Login />,
    layout: "/public",
  },
  {
    path: "/register-patient",
    name: "Register",
    icon: "ni ni-circle-08 text-pink",
    component: <RegisterPatient />,
    layout: "/public",
  },
  {
    path: "/forgotten-password",
    name: "Forgotten password",
    icon: "ni ni-circle-08 text-pink",
    component: <ForgottenPassword />,
    layout: "/public",
  },
  {
    path: "/verify-email",
    name: "Verify account",
    icon: "ni ni-circle-08 text-pink",
    component: <VerifyAccount />,
    layout: "/public",
  },
  {
    path: "/register-admin",
    name: "Create admin",
    icon: "ni ni-circle-08 text-pink",
    component: <RegisterAdmin />,
    layout: "/public",
  },
  {
    path: "/register-doctor",
    name: "Join as doctor",
    icon: "ni ni-circle-08 text-pink",
    component: <RegisterDoctor />,
    layout: "/public",
  },
  {
    path: "/patients/:patientId",
    name: "Patient Profile",
    icon: "ni ni-single-02 text-primary",
    component: <PatientProfile />,
    layout: "/health",
  },
  {
    path: "/doctors/:doctorId",
    name: "Doctor Profile",
    icon: "ni ni-single-02 text-primary",
    component: <DoctorProfile />,
    layout: "/health",
  },
  {
    path: "/doctors/edit-profile",
    name: "Edic Doctor Profile",
    icon: "ni ni-single-02 text-primary",
    component: <EditDoctorProfile />,
    layout: "/health",
  },
  {
    path: "/patients/edit-profile",
    name: "Edic Patient Profile",
    icon: "ni ni-single-02 text-primary",
    component: <EditPatientProfile />,
    layout: "/health",
  },
  {
    path: "/medicines/create",
    name: "Create new medicine",
    icon: "ni ni-single-02 text-primary",
    component: <CreateMedicine />,
    layout: "/health",
  },
  {
    path: "/medicines/update/:medId",
    name: "Update a medicine",
    icon: "ni ni-single-02 text-primary",
    component: <UpdateMedicine />,
    layout: "/health",
  },
  {
    path: "/medicines",
    name: "Medicines list",
    icon: "ni ni-single-02 text-primary",
    component: <MedicineList />,
    layout: "/common",
  },
  {
    path: "/medicines/:medId",
    name: "Medicine details",
    icon: "ni ni-single-02 text-primary",
    component: <MedicineDetails />,
    layout: "/common",
  },
  {
    path: "/community",
    name: "Community",
    icon: "ni ni-single-02 text-primary",
    component: <Community />,
    layout: "/health",
  },

  {
    path: "/diseases/create",
    name: "Diseases",
    icon: "ni ni-single-02 text-primary",
    component: <CreateDisease />,
    layout: "/health",
  },
];
export default routes;