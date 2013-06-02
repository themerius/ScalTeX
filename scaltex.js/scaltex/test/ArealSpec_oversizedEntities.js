describe("Areal with oversized Entities", function() {
  var areal, page, pageFactory, w, seq, arealElement;

  beforeEach(function() {  // init Classes
    page = document.createElement("script");
    page.id = "pageTypeX";
    page.innerHTML = "<div class=\"pageTypeX\">" +
      "<div id=\"{{{appendPoint_0}}}\" class=\"mainContent\"></div>" +
      "</div>";
    document.body.appendChild(page);

    pageFactory = new scaltex.PageFactory({
      pageTypeX: {
        template: "pageTypeX",
        appendPoints: [
          {type: "content", templateVariable: "appendPoint_0", maxHeight: 270}
        ]
      }
    });

    w = document.createElement("script");
    w.id = "entityTypeW";
    w.innerHTML = '<div style="width: 50px">{{w}}</div>';
    document.body.appendChild(w);

    seq = [
      {
        pageType: "pageTypeX",
        entities: [  // they have height of 108px each.
          {templateId: "entityTypeW", json: {id: 0, w: "words are splitted by space seperator.", splitable: true, splitVar: "w"}},
          {templateId: "entityTypeW", json: {id: 1, w: "words are splitted by space seperator.", splitable: true, splitVar: "w"}},
          {templateId: "entityTypeW", json: {id: 2, w: "words are splitted by space seperator.", splitable: true, splitVar: "w"}},
          {templateId: "entityTypeW", json: {id: 3, w: "words are splitted by space seperator.", splitable: true, splitVar: "w"}}
        ]
      }
    ];

    arealElement = document.createElement("div");
    arealElement.id = "Areal_0";
    document.body.appendChild(arealElement);


    areal = new scaltex.Areal("Areal_0", seq, pageFactory);
  });

  afterEach(function() {
    document.body.removeChild(page);
    document.body.removeChild(w);
    document.body.removeChild(arealElement);
  });

  it("should be able to split the entities, so that they fit on the remaining space", function() {
    areal.generateEntities();
    areal.renderEntities();
    areal.mountEntitiesToConstructionArea();
    areal.moveEntitiesToNewPages();

    expect(document.getElementById("Areal_0_Page_1")
      .innerHTML).toEqual([
        '<div class="pageTypeX">',
          '<div id="content_Areal_0_Page_1" class="mainContent">',
            '<div id="Entity_0"><div style="width: 50px">',
              'words are splitted by space seperator.</div></div>',
            '<div id="Entity_1"><div style="width: 50px">',
              'words are splitted by space seperator.</div></div>',
            '<div id="Entity_2a"><div style="width: 50px">',
              'words are splitted</div></div>',
          '</div>',
        '</div>'].join(''));

    expect(document.getElementById("Areal_0_Page_2")
      .innerHTML).toEqual([
        '<div class="pageTypeX">',
          '<div id="content_Areal_0_Page_2" class="mainContent">',
            '<div id="Entity_2b"><div style="width: 50px">',
              'by space seperator.</div></div>',
            '<div id="Entity_3"><div style="width: 50px">',
              'words are splitted by space seperator.</div></div>',
          '</div>',
        '</div>'].join(''));
  });

});
