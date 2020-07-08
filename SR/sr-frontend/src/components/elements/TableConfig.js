export const TableMethod = {
  sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  },
  filterChanged(filter) {
    this.filter = filter;
  },
  pageChanged(page) {
    this.page = page;
  },
  async callRows(indexesToLoad) {
    await this.sleep(1000);
    return indexesToLoad.map(() => {
      return {
        name: "Herrod Chandler",
        function: "Sales Assistant",
        city: "San Francisco",
        id: "9608",
        since: "2012/08/06",
        budget: 137500
      };
    });
  },
  async callChildren(parent) {
    console.log(parent);
    await this.sleep(1000);
    return [
      {
        name: "Jonas Alexander",
        function: "Developer",
        city: "San Francisco",
        id: "8196",
        since: "2010/07/14",
        budget: 86500
      }
    ];
  },
  async callTempRows() {
    await this.sleep(1000);
    return {
      rows: [],
      total: 0
    };
  },
  deleteRows() {
    let me = this;
    this.rows = delRows(this.rows);

    function delRows(rows) {
      return rows.filter(row => {
        if (row._children && row._children.length) {
          row._children = delRows(row._children);
        }
        return me.selectedRowIds.indexOf(row.id) === -1;
      });
    }
  },
  async addRows() {
    await this.sleep(1000);
    let employees = [
      {
        name: "Alexander Ivanov",
        function: "Developer",
        city: "Saint Petersburg",
        id: "1990",
        since: "2011/08/21",
        budget: 180000
      },
      {
        name: "Nikolay Petrov",
        function: "QA",
        city: "Tula",
        id: "1995",
        since: "2015/07/15",
        budget: 120000
      },
      {
        name: "Sergey Stolyarov",
        function: "Architect",
        city: "Moscow",
        id: "1985",
        since: "2012/03/07",
        budget: 320000
      }
    ];
    this.rows.push(...employees);
  }
};
