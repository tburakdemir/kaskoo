"use client";
import { useState } from "react";
import { FormControl, InputLabel, Select, MenuItem, Box } from "@mui/material";
import Grid from "@mui/material/Grid";
import Chart from "react-apexcharts";
import { useTheme, useMediaQuery } from "@mui/material";
import DarkModeToggle from "./components/ThemeToggle";

export default function Home() {
  const [year, setYear] = useState("2011");
  const [brand, setBrand] = useState({ brand: "", brandCode: "" });
  const [brands, setBrands] = useState([]);
  const [model, setModel] = useState({ model: "", modelCode: "" });
  const [models, setModels] = useState([]);
  const [insuranceList, setInsuranceList] = useState([]);

  const [chart, setChart] = useState({
    options: {
      chart: {
        id: "apexchart-example",
      },
      xaxis: {
        categories: insuranceList.map(
          (item) => `${item.year}-${item.month.toString().padStart(2, "0")}`
        ),
      },
    },
    series: [
      {
        name: "series-1",
        data: insuranceList.map((item) => item.tlPrice),
      },
    ],
  });

  const currentYear = new Date().getFullYear();
  const years = Array.from({ length: 15 }, (_, index) =>
    (currentYear - index).toString()
  );

  const handleYearChange = (e) => {
    setYear(e.target.value);
    setBrand({ brand: "", brandCode: "" });
    setModel({ model: "", modelCode: "" });
    fetchBrands(year);
  };

  const fetchBrands = async (year) => {
    const response = await fetch(
      `http://localhost:8080/api/vehicles/brands?year=${year}`
    );
    const data = await response.json();
    setBrands(data);
  };

  const handleBrandChange = (e) => {
    const selectedBrand = brands.find((b) => b.brand === e.target.value);
    setBrand(selectedBrand);
    setModel({ model: "", modelCode: "" });
    fetchModels(selectedBrand.brandCode, year);
  };

  const fetchModels = async (brandCode, year) => {
    const response = await fetch(
      `http://localhost:8080/api/vehicles/models?brandCode=${brandCode}&year=${year}`
    );
    const data = await response.json();
    setModels(data);
  };

  const handleModelChange = (e) => {
    const selectedModel = models.find((m) => m.model === e.target.value);
    setModel(selectedModel);
    fetchInsuranceList(selectedModel.modelCode, year);
  };

  const fetchInsuranceList = async (modelCode, year) => {
    const response = await fetch(
      `http://localhost:8080/api/insurances/with-currencies/${brand.brandCode}/${modelCode}/${year}`
    );
    const data = await response.json();
    data.sort((a, b) => {
      return new Date(a.year, a.month) - new Date(b.year, b.month);
    });
    setInsuranceList(data);
    setChart({
      options: {
        ...chart.options,
        xaxis: {
          categories: data.map(
            (item) => `${item.year}-${item.month.toString().padStart(2, "0")}`
          ),
          tickPlacement: "on",
          type: "x",
        },
      },
      series: [
        {
          name: "Min Wage",
          data: data.map((item) =>
            parseFloat((item.tlPrice / item.minWageTry).toFixed(2))
          ),
        },
        {
          name: "Usd",
          data: data.map((item) =>
            parseFloat((item.tlPrice / item.usdTry).toFixed(2))
          ),
        },
        {
          name: "Xau",
          data: data.map((item) =>
            parseFloat((item.tlPrice / item.xauTryg).toFixed(2))
          ),
        },
      ],
    });
  };

  const theme = useTheme();
  const is4K = useMediaQuery("(min-width:3840px)");
  const is2K = useMediaQuery("(min-width:2560px) and (max-width:3839px)");
  const is1080p = useMediaQuery("(min-width:1920px) and (max-width:2559px)");
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));

  return (
    <div>
      <nav>
        <DarkModeToggle />
      </nav>
      <Box
        sx={{
          flexGrow: 1,
          p: isMobile ? 2 : is4K ? 6 : 4,
          minHeight: "100vh",
          display: "flex",
          flexDirection: "column",
          alignItems: "center", // Add this line
          justifyContent: "center", // Add this line
          background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
          padding: isMobile ? 2 : 4,
        }}
      >
        <Grid container spacing={isMobile ? 2 : is4K ? 6 : 4}>
          {/* Top Grid for Dropdowns/Selectors */}
          <Grid
            sx={{ justifyContent: "center" }}
            item
            xs={12}
            container
            spacing={isMobile ? 2 : is4K ? 6 : 4}
          >
            <Grid item xs={12} sm={6} md={3}>
              <Box
                sx={{
                  backgroundColor: "rgba(255, 255, 255, 0.9)",
                  borderRadius: 4,
                  padding: 4,
                  boxShadow: "0 8px 32px rgba(0, 0, 0, 0.1)",
                  marginBottom: 6,
                  width: "fit-content",
                  minWidth: "100%",
                }}
              >
                <Grid container spacing={3} direction="column">
                  <Grid item xs={12}>
                    <FormControl fullWidth size="large">
                      <InputLabel id="year-label">Year</InputLabel>
                      <Select
                        labelId="year-label"
                        value={year}
                        label="Year"
                        onChange={handleYearChange}
                      >
                        <MenuItem value="">
                          <em>None</em>
                        </MenuItem>
                        {years.map((y) => (
                          <MenuItem key={y} value={y}>
                            {y}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12}>
                    <FormControl fullWidth size="large">
                      <InputLabel id="brand-label">Brand</InputLabel>
                      <Select
                        labelId="brand-label"
                        value={brand.brand}
                        label="Brand"
                        onChange={handleBrandChange}
                      >
                        <MenuItem value="">
                          <em>None</em>
                        </MenuItem>
                        {brands.map((b) => (
                          <MenuItem key={b.brandCode} value={b.brand}>
                            {b.brand}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12}>
                    <FormControl fullWidth size="large">
                      <InputLabel id="model-label">Model</InputLabel>
                      <Select
                        labelId="model-label"
                        value={model.model}
                        label="Model"
                        onChange={handleModelChange}
                      >
                        <MenuItem value="">
                          <em>None</em>
                        </MenuItem>
                        {models.map((m) => (
                          <MenuItem key={m.modelCode} value={m.model}>
                            {m.model}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                </Grid>
              </Box>
            </Grid>
          </Grid>

          {/* Chart Grid */}
          {insuranceList.length > 0 && (
            <Grid item xs={12}>
              <Box
                sx={{
                  backgroundColor: "rgba(255, 255, 255, 0.9)",
                  borderRadius: isMobile ? 2 : 4,
                  padding: isMobile ? 2 : is4K ? 6 : 4,
                  boxShadow: "0 8px 32px rgba(0, 0, 0, 0.1)",
                  width: "100%",
                  marginTop: isMobile ? 3 : is4K ? 8 : 6,
                  maxWidth: is4K
                    ? "3200px"
                    : is2K
                    ? "2400px"
                    : is1080p
                    ? "1800px"
                    : "1400px",
                  mx: "auto",
                }}
              >
                <Chart
                  options={{
                    ...chart.options,
                    chart: {
                      ...chart.options.chart,
                      width: "100%",
                    },
                  }}
                  series={chart.series}
                  type="line"
                  width="100%"
                  height={
                    is4K
                      ? 1000
                      : is2K
                      ? 800
                      : is1080p
                      ? 600
                      : isMobile
                      ? 300
                      : 500
                  }
                />
              </Box>
            </Grid>
          )}
        </Grid>
      </Box>
    </div>
  );
}
