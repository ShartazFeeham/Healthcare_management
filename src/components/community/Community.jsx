import { Col, Container, Row } from "reactstrap";
import Post from "./Post";
import Status from "./Status";
import FAQ from "./FAQ";
import Articles from "./Articles";
import FirstAid from "./FirstAid";

const Community = () => {
  return (
    <>
      <>
        <div
          className="header pb-5 pt-5 pt-lg-8 d-flex align-items-center"
          style={{
            height: "100px",
            backgroundImage:
              "url(" + require("../../assets/img/cover/health5.jpg") + ")",
            backgroundSize: "cover",
            backgroundPosition: "center top",
          }}
        >
          <span className="mask bg-gradient-default opacity-8" />
          <Container className="d-flex align-items-center" fluid>
            <Row>
              <h1 className="display-2 opacity-7" style={{ color: "white" }}>
                Community
              </h1>
            </Row>
          </Container>
        </div>
      </>
      <Container className="mt-4" fluid>
        <Row>
          <Col className="order-xl-1" xl="6">
            <Post />
            <h2 className="m-3">Community feeds</h2>
            <Status />
            <h2 className="m-3">Frequently Asked Questions</h2>
            <FAQ />
          </Col>

          <Col className="order-xl-1" xl="6">
            <Articles />
            <FirstAid />
            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ut illum
            inventore veritatis? Recusandae temporibus optio soluta quam
            repudiandae quasi debitis aut, dicta tempora voluptate, nostrum
            eligendi. Dicta, animi accusantium? Iste harum explicabo, soluta
            numquam neque veritatis architecto! Odio beatae placeat harum illum
            laborum dolores perferendis? Ex, provident sunt libero sed soluta
            voluptatum ab debitis reprehenderit nisi, voluptatem, cum quaerat
            aliquam. Mollitia qui enim velit maxime excepturi necessitatibus
            veritatis. Obcaecati, repudiandae corrupti. Minima distinctio unde
            neque! Unde, earum fugiat! Et veniam aliquam beatae ut, commodi
            nihil ipsum illo aliquid fugit saepe non, asperiores voluptas culpa,
            repellendus debitis quae quas architecto! Amet omnis dolore odio
            quia molestiae fugit illo voluptates reprehenderit, nobis id error
            deleniti possimus libero. Est consequatur provident perspiciatis
            soluta, aspernatur unde libero debitis illum expedita, culpa
            perferendis, officiis totam suscipit? Consequatur quod vitae,
            officia rem numquam ratione, nemo nam nisi distinctio ea veniam
            facere consequuntur eaque nesciunt ipsam laborum totam dolorum. In
            fuga atque, hic doloribus incidunt placeat expedita eligendi nemo.
            Nihil culpa in molestiae magni eligendi, tempora commodi similique
            dolor exercitationem doloremque corrupti deserunt. Distinctio vitae,
            voluptatibus dolores quia minima aut, a beatae perspiciatis eligendi
            nesciunt voluptates eum dolore soluta ea facere. Facilis laborum
            commodi quaerat neque, distinctio temporibus soluta, accusamus
            consequuntur tempore eos maiores eum cum reprehenderit perspiciatis
            laboriosam voluptas obcaecati minima provident? Error accusamus,
            nobis non obcaecati nihil incidunt pariatur consequuntur!
            Repellendus consequuntur nostrum ullam cumque, at nam labore,
            consequatur qui vel corrupti, harum nulla maiores molestias illo
            dignissimos temporibus! Architecto quibusdam facilis doloribus
            quaerat, vero inventore commodi sint optio laborum et distinctio,
            tempora eligendi, a in debitis. Praesentium, omnis excepturi
            molestiae quae quaerat deleniti reiciendis deserunt facere libero
            nemo aliquam ex amet unde rem, delectus quisquam repudiandae. Minus
            quasi in sapiente perspiciatis laudantium ex aut quaerat quo,
            aliquid expedita voluptate repellendus recusandae enim omnis libero
            cumque culpa, ipsa ad cum fugiat. Temporibus reprehenderit optio
            blanditiis inventore aspernatur sunt ut perferendis odio. Quasi
            repellendus numquam perferendis sint assumenda illum deserunt,
            aspernatur molestiae pariatur unde itaque, saepe reiciendis alias ab
            repellat doloribus a, suscipit veritatis libero. Distinctio
            quibusdam doloremque consequatur facere sit cupiditate nisi, iusto
            commodi. Praesentium ex in maxime dolor culpa? Error veniam dolorem
            ex omnis dignissimos maiores minus. Minus, fugit architecto earum
            repellendus sint soluta vero. Vitae, cupiditate! Nam recusandae eum
            placeat! Numquam, unde reiciendis? Incidunt illo magnam molestiae
            cum veniam saepe doloribus voluptatem debitis in reprehenderit nisi
            numquam velit inventore, cumque distinctio nam accusamus rerum alias
            at, nihil possimus aspernatur voluptatibus sunt quidem. Dignissimos
            libero esse nisi quam minima quaerat! Quia quos quas voluptate
            nulla. Soluta a similique eaque at maiores ex sint culpa fuga vitae
            quas facere, eius dolorem eum esse. Reprehenderit ut accusantium,
            saepe, adipisci qui laudantium est obcaecati fuga, praesentium vel
            ullam totam non aliquid. Culpa blanditiis quod qui, voluptatem harum
            fugiat, error praesentium quos et modi quaerat quae officiis,
            recusandae temporibus asperiores eaque rerum illum doloribus vel
            distinctio. Nihil, nostrum recusandae iste culpa sunt minima totam
            possimus officiis quae veniam, id, atque tempora quos perferendis
            illum dolores voluptatibus aliquid nobis?
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Community;
