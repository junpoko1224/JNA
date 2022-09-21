package jna.example.training.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jna.example.training.domain.object.*;
import jna.example.training.domain.service.DeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 拡張機能を指定
@ExtendWith(SpringExtension.class)
// SpringBootの機能を用いる処理のテストを行う場合に付与
// テスト実行時のコンフィグレーション（設定）クラスを自動検出する機能を備えている
@SpringBootTest
// MockMvcを使用する場合に付与
@AutoConfigureMockMvc
public class DeleteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Controller 内で DI している Service は mock にする
    // モックに置き換えたいインスタンスに付与（すべてのメソッドがモックになる）
    @MockBean
    private DeleteService deleteService;

    @Autowired
    DeleteController target;

    ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
        mapper = new ObjectMapper();
    }

    // テストの表示名
    @DisplayName("Normal Test")
    // テスト対象
    @Test
    public void normal_test() throws Exception {

        String empNo = "123456";

        // モック化したメソッドの動作を定義
        doNothing().when(deleteService).delete(empNo);

        // MockMvcはControllerのテストとして対象のパスにリクエストを送信するために使用する
        // /delete+empNoにGETメソッドでリクエストを送信し、
        mockMvc.perform(MockMvcRequestBuilders.get("/delete/" + empNo))
                // completeのフラッシュメッセージが表示されることをテストする
                .andExpect(flash().attribute("complete", "削除が完了しました"))
                // レスポンスのステータスコードが302であることを検証する
                .andExpect(status().isFound());

        // モック化したインスタンスがきちんと呼び出されたかの確認
        verify(deleteService).delete(empNo);

    }

    @DisplayName("Exceptional Test")
    @Test
    public void exceptional_test() throws Exception {

        String empNo = "12345";

        doNothing().when(deleteService).delete(empNo);

        mockMvc.perform(MockMvcRequestBuilders.get("/delete/" + empNo))
                //
                .andExpect(status().isFound());

        //verify(deleteService, never()).delete(empNo);
        verify(deleteService).delete(empNo);

    }
}