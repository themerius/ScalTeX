package scaltex.template.ecowire

import scaltex.buildtools._
import scaltex.buildtools.logic._
import scaltex.types.academic
import play.api.libs.json._

trait EcowireReportTemplate extends TemplateStock {
  override def headerTemplate (title: String) = s"""
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="utf-8" />
    <title>$title</title>
    <link rel="stylesheet" href="ecowire_css/reset.css" />
    <link rel="stylesheet" href="ecowire_css/typo.css" />
    <link rel="stylesheet" href="ecowire_css/grid.css" />
    <link rel="stylesheet" href="ecowire_css/titlepage.css" />
    <link rel="stylesheet" href="ecowire_css/toc.css" />
    <script src="http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js"></script>
    <script src="../../scaltex.js/scaltex/src/scaltex.js"></script>
    </head>
    <body>
  """

  footerTemplate = """
    </body>
    </html>
  """

  addTemplateEntity (
    "main entities",
    """
    <script id="page" type="text/template">
    <div class="pageA4">
      <div id="{{{appendPoint_content}}}" class="layoutGrid"></div>
      <div id="{{{appendPoint_footer}}}"></div>
    </div>
    </script>

    <script id="pagina" type="text/template">
    <div class="layoutGridBottom pagina">
      <div class="row line">
        <div class="col3">
          <p>{{institutName}}</p>
        </div>
        <div class="col1 right">
         <p>{{pageNr}}</p>
        </div>
        <div class="row-end">&nbsp;</div>
    </div>
    </script>

    <script id="text_1110" type="text/template">
    <div class="row">
      <div class="col3">
        <p>{{{text}}}</p>
        {{#newline}}
        </br>
        {{/newline}}
      </div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>

    <script id="heading" type="text/template">
    <div class="row">
      <div class="col4"><{{h}}>{{number}}</br>{{heading}}</{{h}}></br></br></div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>

    <script id="figure_1100" type="text/template">
    <div class="row topSpace bottomSpace">
      <div class="col2">
        <img class="scaling" src="{{src}}" alt="{{description}}" />
      </div>
      <div class="col1">  <!-- style="margin-top: parentHight - self.hight px" -->
        <strong>Abbildung {{number}}: {{{description}}}</strong>
      </div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>

    <script id="figure_1110" type="text/template">
    <div class="row topSpace bottomSpace">
      <div class="col3">
        <img class="scaling" src="{{src}}" alt="{{description}}" />
      </div>
      <div class="col1">  <!-- style="margin-top: parentHight - self.hight px" -->
        <strong>Abbildung {{number}}: {{{description}}}</strong>
      </div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>
    """)

  addTemplateEntity (
    "toc",
    """
    <script id="toc_heading" type="text/template">
    <div class="row tocHeading">
      <div class="col4"><h1>{{heading}}</h1></div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>

    <script id="toc_mainSection" type="text/template">
    <div class="row tocMainSection">
      <div class="col3 tocLine">
        <span class="tocTitle tocFloatingDots">{{nr}}</br>{{title}} . . . . . . .
          . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
          . . . . . . . . . . . . . . . . . . . . . . . . . . . </span>
        <span class="tocNumbering"></br>&nbsp;{{page}}</span>
      </div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>

    <script id="toc_section" type="text/template">
    <div class="row">
      <div class="col3 tocLine">
        <span class="tocTitle tocFloatingDots">{{nr}}</br>{{title}} . . . . . . .
          . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
          . . . . . . . . . . . . . . . . . . . . . . . . . . . </span>
        <span class="tocNumbering"></br>&nbsp;{{page}}</span>
      </div>
      <div class="row-end">&nbsp;</div>
    </div>
    </script>
    """)

addTemplateEntity(
  "titlepage",
  """
  <script id="titlepage" type="text/template">
  <div class="pageA4">
    <div id="{{{appendPoint_logo}}}"></div>
    <div class="layoutGrid">
      <div id="{{{appendPoint_longName}}}"></div>
      <div id="{{{appendPoint_mainTitle}}}"></div>
      <div id="{{{appendPoint_partner}}}"></div>
    </div>
  </div>
  </script>

  <script id="titlepage_logo" type="text/template">
  <img class="titlepage_logo" src="{{src}}" alt="{{description}}" />
  </script>

  <script id="titlepage_longName" type="text/template">
  <div class="row">
    <div class="col4">
      <p class="titlepage_longName">{{{text}}}</p>
    </div>
    <div class="row-end">&nbsp;</div>
  </div>
  </script>

  <script id="titlepage_mainTitle" type="text/template">
  <div class="row">
    <div class="col3">
      <p class="titlepage_main">{{{main}}}</p>
      <p class="titlepage_sub">{{{sub}}}</p>
    </div>
    <div class="row-end">&nbsp;</div>
  </div>
  </script>

  <script id="titlepage_partner" type="text/template">
  <div class="titlepage_partner">
    <p>In Zusammenarbeit mit</p>
    </br>
    <img class="titlepage_partnerlogo" src="{{src}}" alt="{{description}}" />
  </div>
  </script>
  """)

}

abstract class Heading (val heading: String) extends Entity with SectionNumber {
  var appendPoint = "content"
  val templateId = "heading"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "heading" -> Json.toJson(heading),
          "number" -> Json.toJson(sectionNumber),
          "h" -> Json.toJson(h)
        )
      )
    )
  ).toString
}

class HeadingChapter (heading: String) extends Heading (heading) with Chapter
class HeadingSection (heading: String) extends Heading (heading) with Section
class HeadingSubSection (heading: String) extends Heading (heading) with SubSection
class HeadingSubSubSection (heading: String) extends Heading (heading) with SubSubSection


class Text (txt: () => String) extends Entity {
  def this (txt: String) = this(() => txt)
  var appendPoint = "content"
  val templateId = "text_1110"
  var newlineList: List[Boolean] = List()
  def newline: Text = {
    newlineList = true :: newlineList
    this
  }
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "text" -> Json.toJson(txt()),
          "newline" -> Json.toJson(newlineList),
          "splitable" -> Json.toJson(true),
          "splitVar" -> Json.toJson("text")
        )
      )
    )
  ).toString
}

class Figure (src: String, desc: String) extends Entity with FigureNumber {
  var appendPoint = "content"
  val templateId = "figure_1100"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "src" -> Json.toJson(src),
          "description" -> Json.toJson(desc),
          "number" -> Json.toJson(figureNumber)
        )
      )
    )
  ).toString
}

class Figure1110 (src: String, desc: String) extends Entity with FigureNumber {
  var appendPoint = "content"
  val templateId = "figure_1110"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "src" -> Json.toJson(src),
          "description" -> Json.toJson(desc),
          "number" -> Json.toJson(figureNumber)
        )
      )
    )
  ).toString
}

class TOCHead (heading: String) extends Entity {
  var appendPoint = "content"
  val templateId = "toc_heading"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "heading" -> Json.toJson(heading)
        )
      )
    )
  ).toString
}

class TOCMainSection (nr: String, title: String, inId: Int) extends Entity {
  var appendPoint = "content"
  val templateId = "toc_mainSection"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "nr" -> Json.toJson(nr),
          "title" -> Json.toJson(title),
          "page" -> Json.toJson(s"@inId(${inId})")
        )
      )
    )
  ).toString
}

class TOCSection (nr: String, title: String, inId: Int) extends Entity {
  var appendPoint = "content"
  val templateId = "toc_section"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "id" -> Json.toJson(id),
          "nr" -> Json.toJson(nr),
          "title" -> Json.toJson(title),
          "page" -> Json.toJson(s"@inId(${inId})")
        )
      )
    )
  ).toString
}

class TitlepageLogo (src: String, description: String) extends Entity {
  var appendPoint = "logo"
  val templateId = "titlepage_logo"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "appendPoint" -> Json.toJson(appendPoint),
          "id" -> Json.toJson(id),
          "src" -> Json.toJson(src),
          "description" -> Json.toJson(description)
        )
      )
    )
  ).toString
}

class TitlepageLongName (name: String) extends Entity {
  var appendPoint = "longName"
  val templateId = "titlepage_longName"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "appendPoint" -> Json.toJson(appendPoint),
          "id" -> Json.toJson(id),
          "text" -> Json.toJson(name)
        )
      )
    )
  ).toString
}

class TitlepageMainTitle (main: String, sub: String) extends Entity {
  var appendPoint = "mainTitle"
  val templateId = "titlepage_mainTitle"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "appendPoint" -> Json.toJson(appendPoint),
          "id" -> Json.toJson(id),
          "main" -> Json.toJson(main),
          "sub" -> Json.toJson(sub)
        )
      )
    )
  ).toString
}

class TitlepagePartner (src: String, description: String) extends Entity {
  var appendPoint = "partner"
  val templateId = "titlepage_partner"
  def toJson = Json.toJson(
    Map(
      "templateId" -> Json.toJson(templateId),
      "json" -> Json.toJson(
        Map(
          "appendPoint" -> Json.toJson(appendPoint),
          "id" -> Json.toJson(id),
          "src" -> Json.toJson(src),
          "description" -> Json.toJson(description)
        )
      )
    )
  ).toString
}

class PageA4 extends Page {
  val appendPoints = {
    Map(
      "type" -> "content",
      "templateVariable" -> "appendPoint_content",
      "maxHeight" -> "241.3mm") ::
    Map(
      "type" -> "footer",
      "templateVariable" -> "appendPoint_footer",
      "maxHeight" -> "12.6mm") :: Nil
  }
  val templateId = "page"
  val officialName = "A4"
}

class A4Titlepage extends Page {
  val appendPoints = {
    Map(
      "type" -> "logo",
      "templateVariable" -> "appendPoint_logo",
      "maxHeight" -> "241.3mm") ::
    Map(
      "type" -> "longName",
      "templateVariable" -> "appendPoint_longName",
      "maxHeight" -> "241.3mm") ::
    Map(
      "type" -> "mainTitle",
      "templateVariable" -> "appendPoint_mainTitle",
      "maxHeight" -> "241.3mm") ::
    Map(
      "type" -> "partner",
      "templateVariable" -> "appendPoint_partner",
      "maxHeight" -> "241.3mm") :: Nil
  }
  val templateId = "titlepage"
  val officialName = "A4_titlepage"
}

class EcowireReportEntityBinding extends academic.EntityBinding {
  override def $ (refName: String): EcowireReportEntityBinding = {
    nextRefName = refName
    return this
  }
  def § (h: String)(implicit areal: Areal): HeadingChapter = {
    val heading = new HeadingChapter(h)
    heading.bindToAreal(areal)
    registerReference(heading)
    return heading
  }
  def §§ (h: String)(implicit areal: Areal): HeadingSection = {
    val heading = new HeadingSection(h)
    heading.bindToAreal(areal)
    registerReference(heading)
    return heading
  }
  def §§§ (h: String)(implicit areal: Areal): HeadingSubSection = {
    val heading = new HeadingSubSection(h)
    heading.bindToAreal(areal)
    registerReference(heading)
    return heading
  }
  def §§§§ (h: String)(implicit areal: Areal): HeadingSubSubSection = {
    val heading = new HeadingSubSubSection(h)
    heading.bindToAreal(areal)
    registerReference(heading)
    return heading
  }
  def txt (t: String)(implicit areal: Areal): Text = {
    val text = new Text(t)
    text.bindToAreal(areal)
    registerReference(text)
    return text 
  }
  def txt (t: () => String)(implicit areal: Areal): Text = {
    val text = new Text(t)
    text.bindToAreal(areal)
    registerReference(text)
    return text
  }
  def figure (src: String, desc: String)(implicit areal: Areal): Figure = {
    val fig = new Figure(src, desc)
    fig.bindToAreal(areal)
    registerReference(fig)
    return fig
  }
  def figure1110 (src: String, desc: String)(implicit areal: Areal): Figure1110 = {
    val fig = new Figure1110(src, desc)
    fig.bindToAreal(areal)
    registerReference(fig)
    return fig
  }
}

class EcowireTitlepageEntityBinding extends scaltex.buildtools.EntityBinding {
  override def $ (refName: String): EcowireTitlepageEntityBinding = {
    nextRefName = refName
    return this
  }
  def logo (src: String, description: String)(implicit areal: Areal): TitlepageLogo = {
    val tl = new TitlepageLogo(src, description)
    tl.bindToAreal(areal)
    registerReference(tl)
    return tl
  }
  def name (name: String)(implicit areal: Areal): TitlepageLongName = {
    val tn = new TitlepageLongName(name)
    tn.bindToAreal(areal)
    registerReference(tn)
    return tn
  }
  def title (main: String, sub: String)(implicit areal: Areal): TitlepageMainTitle = {
    val tt = new TitlepageMainTitle(main, sub)
    tt.bindToAreal(areal)
    registerReference(tt)
    return tt
  }
  def partner (src: String, description: String)(implicit areal: Areal): TitlepagePartner = {
    val tp = new TitlepagePartner(src, description)
    tp.bindToAreal(areal)
    registerReference(tp)
    return tp
  }
}

object Document extends Tray[EntityPageBase]

class Document (implicit builder: Builder=null) extends Areal with scaltex.util.$StringContext {
  val companion: Tray[EntityPageBase] = Document
  var appendPoint = "Document"
  val defaultPage = new PageA4
  setCurrentPage(defaultPage)
  val ++ = new EcowireReportEntityBinding
  def newpage = {
    addToList(getCurrentPage)
    this
  }
  def page_to (p: Page) = {
    setCurrentPage(p)
    addToList(getCurrentPage)
    this
  }
}

object TableOfContents extends Tray[EntityPageBase]

class TableOfContents (areals: (() => Areal)*)(implicit builder: Builder=null) extends Areal {
  val companion: Tray[EntityPageBase] = TableOfContents
  var appendPoint = "TableOfContents"
  val defaultPage = new PageA4
  setCurrentPage(defaultPage)
  val ++ = new EcowireReportEntityBinding
  override val pageNumberStyle = "roman"
  def newpage = this
  def page_to (p: Page) = this

  addToList(new TOCHead("Inhalt"))

  def create = {
    val unpacked = areals.map(a => a())
    for (areal <- unpacked) {
      areal.companion.get.foreach { x =>
        x match {
          case c: HeadingChapter => addToList(new TOCMainSection(c.sectionNumber, c.heading, c.id))
          case s: HeadingSection => addToList(new TOCSection(s.sectionNumber, s.heading, s.id))
          case s: HeadingSubSection => addToList(new TOCSection(s.sectionNumber, s.heading, s.id))
          case _ => None
        }
      }
    }
  }
}

object Titlepage extends Tray[EntityPageBase]

class Titlepage (implicit builder: Builder=null) extends Areal with scaltex.util.$StringContext {
  val companion: Tray[EntityPageBase] = Titlepage
  var appendPoint = "Titlepage"
  val defaultPage = new A4Titlepage
  setCurrentPage(defaultPage)
  val ++ = new EcowireTitlepageEntityBinding
  def newpage = {
    addToList(getCurrentPage)
    this
  }
  def page_to (p: Page) = {
    setCurrentPage(p)
    addToList(getCurrentPage)
    this
  }
}

object EcowireReportBuilder extends Tray[Areal]

class EcowireReportBuilder extends Builder with EcowireReportTemplate with scaltex.util.$StringContext {
  val companion = EcowireReportBuilder
  val allPages = new PageA4 :: new A4Titlepage :: Nil
  override val set = this
  var institutName = "EcoWire"
  def institut_name (name: String) = institutName = name
  def generateJsSpecialEntities = s"""
    var specialEntities = [
      {
        templateId: "pagina",
        json: {institutName: "$institutName", pageNr: "@nextPageNr"},
        requiredPageAppendPoint: "footer"
      }
    ];
  """
}